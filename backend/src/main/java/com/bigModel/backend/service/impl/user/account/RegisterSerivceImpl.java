package com.bigModel.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigModel.backend.mapper.UserMapper;
import com.bigModel.backend.pojo.User;
import com.bigModel.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RegisterSerivceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Map<String, String> register(String username, String password, String confirm_password, String email) {
        Map<String, String> map = new HashMap<>();
        if(username == null){
            map.put("error_message", "用户名不能为空");
            return map;
        }
        if(password == null || confirm_password == null){
            map.put("error_message", "密码不能为空");
            return map;
        }

//        删除空格
        username = username.trim();
        if(username.length() == 0){
            map.put("error_message", "用户名不能为空");
            return map;
        }

        if(password.length() == 0 || confirm_password.length() == 0){
            map.put("error_message", "密码长度不能为空");
            return map;
        }

        if(username.length() > 100){
            map.put("error_message", "用户名长度不能大于100");
            return map;
        }

        if(password.length() > 100 || confirm_password.length() > 100){
            map.put("error_message", "密码长度不能大于100");
            return map;
        }

        if(!password.equals(confirm_password)){
            map.put("error_message", "两次密码不一致");
            return map;
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
//        查询用户
        List<User> users = userMapper.selectList(queryWrapper);
        if(!users.isEmpty()){
            map.put("error_message", "用户名已存在");
            return map;
        }

        String encoded_password = passwordEncoder.encode(password);
        String photo = "";

//        id 是自增
        User user = new User(null,username,encoded_password,photo, null, email);
        userMapper.insert(user);

        map.put("error_message", "success");
        return map;
    }
}
