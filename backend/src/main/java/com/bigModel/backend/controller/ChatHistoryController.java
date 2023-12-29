package com.bigModel.backend.controller;

import com.bigModel.backend.advice.result.Result;
import com.bigModel.backend.service.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Map;

@RestController
@RequestMapping("telegram")
public class ChatHistoryController {
    @Autowired
    private ChatHistoryService chatHistoryService;

    @PostMapping("/findByPage/{page}/{size}")
    public Result findTweetByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size) throws UnsupportedEncodingException, URISyntaxException {

        return Result.success(chatHistoryService.findByPage(page, size));
    }
}
