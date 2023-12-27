package com.bigModel.backend.config.filter;

import com.bigModel.backend.mapper.UserMapper;
import com.bigModel.backend.pojo.User;
import com.bigModel.backend.pojo.UserInformation;
import com.bigModel.backend.service.impl.util.UserDetailsImpl;
import com.bigModel.backend.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//验证jwt-token是否合法，如果合法就自动将user提取到上下文中
//用来验证jwt token，如果验证成功，则将User信息注入上下文中
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private UserMapper userMapper;

    private static String userid;
    @Override
    public void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
//        未来从前端读的 报文  Authorization
        String token = request.getHeader("Authorization");

        if (!StringUtils.hasText(token) || !token.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        token = token.substring(7);

        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        User user = userMapper.selectById(Integer.parseInt(userid));

        if (user == null) {
            throw new RuntimeException("用户名未注册");
        }

        UserDetailsImpl loginUser = new UserDetailsImpl(user);
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);

        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }

    public static UserInformation getUserBasic(){

        UserInformation userInformation = UserInformation.builder().userId(Integer.parseInt(userid)).build();
        return userInformation;
    }
}
