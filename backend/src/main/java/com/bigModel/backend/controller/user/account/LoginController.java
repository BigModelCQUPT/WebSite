package com.bigModel.backend.controller.user.account;

import com.bigModel.backend.advice.ResponseNotIntercept;
import com.bigModel.backend.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@ResponseNotIntercept
@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/account/login")
//    RequestParam 将post请求 从这里面拿出来
    public Map<String, String> getToken(@RequestBody Map<String, String> map){
        String username = map.get("username");
        String password = map.get("password");
        return loginService.getToken(username,password);
    }

}
