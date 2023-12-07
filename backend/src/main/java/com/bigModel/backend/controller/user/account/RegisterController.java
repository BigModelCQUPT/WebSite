package com.bigModel.backend.controller.user.account;

import com.bigModel.backend.advice.ResponseNotIntercept;
import com.bigModel.backend.service.user.account.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@ResponseNotIntercept
@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/user/account/register/")
//    RequestParam 用来从前端获取参数
    public Map<String, String> register(@RequestParam Map<String,String> map){
//        "username"  "password" "confimedPassword" 前端要和他对应 在前端的data里面
        String username = map.get("username");
        String password = map.get("password");
        String confirmedPassword = map.get("confirmedPassword");
        return registerService.register(username,password,confirmedPassword);
    }
}
