package com.bigModel.backend.controller;

import com.bigModel.backend.mapper.ActiveAccountMapper;
import com.bigModel.backend.pojo.ActiveAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("overview")
public class OverviewController {

    @Autowired
    private ActiveAccountMapper activeAccountMapper;

    @GetMapping("account")
    public List<ActiveAccount> getActiveAccount() {
        return activeAccountMapper.getActiveAccount();
    }
}
