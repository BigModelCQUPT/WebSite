package com.bigModel.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bigModel.backend.pojo.ChatHistory;
import com.bigModel.backend.pojo.Tweet;

import java.util.List;

public interface ChatHistoryService extends IService<ChatHistory> {
    IPage<ChatHistory> findByPage(Integer page, Integer size);
    List<ChatHistory> listHistoryByGroupId(String groupId);
    List<ChatHistory> listAllGroupId();
    void saveHistories(List<ChatHistory> chatHistories);
}
