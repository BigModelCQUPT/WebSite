package com.bigModel.backend.controller.user.account;

import com.bigModel.backend.advice.ResponseNotIntercept;
import com.bigModel.backend.service.user.account.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@ResponseNotIntercept
@RestController
public class InfoController {
    @Autowired
    private InfoService infoService;

    @GetMapping("/user/account/info/")
    public Map<String, String> getinfo(){
        return infoService.getinfo();
    }
}
