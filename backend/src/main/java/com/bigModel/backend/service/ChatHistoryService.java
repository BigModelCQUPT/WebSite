package com.bigModel.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.pojo.ChatHistory;
import com.bigModel.backend.pojo.Tweet;

public interface ChatHistoryService {
    IPage<ChatHistory> findByPage(Integer page, Integer size);
}
