package com.bigModel.backend.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bigModel.backend.mapper.ActiveAccountMapper;
import com.bigModel.backend.mapper.KeywordMapper;
import com.bigModel.backend.pojo.ActiveAccount;
import com.bigModel.backend.pojo.Keyword;
import com.bigModel.backend.pojo.TopicKeyword;
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

    @Autowired
    private KeywordMapper topicKeywordMapper;

    @GetMapping("account")
    public List<ActiveAccount> getActiveAccount() {
        return activeAccountMapper.getActiveAccount();
    }

    @GetMapping("topickeyword")
    public List<TopicKeyword> getTopicKeyword(){
        return topicKeywordMapper.getTopicKeyword();
    }
}
