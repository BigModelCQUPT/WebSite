package com.bigModel.backend.controller;

import com.bigModel.backend.advice.result.Result;
import com.bigModel.backend.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("token/add")
public class TokenController {

    @Autowired
    private TokenService tokenService;

    @PostMapping("/mailtoken")
    public Result addMailtoken(@RequestBody Map<String, String> data){
        String mailtoken = data.get("mailtoken");
        tokenService.addMailtoken(mailtoken);
        return Result.success("添加成功");
    }
}
