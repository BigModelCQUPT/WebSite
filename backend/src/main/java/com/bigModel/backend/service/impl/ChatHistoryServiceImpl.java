package com.bigModel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigModel.backend.mapper.ChatHistoryMapper;
import com.bigModel.backend.pojo.ChatHistory;
import com.bigModel.backend.service.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatHistoryServiceImpl implements ChatHistoryService {

    @Autowired
    private ChatHistoryMapper chatHistoryMapper;

    @Override
    public IPage<ChatHistory> findByPage(Integer page, Integer size) {
        Page<ChatHistory> pageHelper = new Page<>(page, size);
        QueryWrapper<ChatHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        IPage<ChatHistory> pageList = chatHistoryMapper.selectPage(pageHelper, queryWrapper);
        Long count = chatHistoryMapper.selectCount(queryWrapper);
        return pageList;
    }
}
