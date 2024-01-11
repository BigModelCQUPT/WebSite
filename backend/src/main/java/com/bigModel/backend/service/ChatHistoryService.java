package com.bigModel.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bigModel.backend.pojo.ChatHistory;

import java.util.List;
import java.util.Map;

public interface ChatHistoryService extends IService<ChatHistory> {
    IPage<ChatHistory> findByPage(Integer page, Integer size, List<String> data);
    List<ChatHistory> listHistoryByGroupId(String groupId);
    List<ChatHistory> listAllGroupId();
    void saveHistories(List<ChatHistory> chatHistories);

    void updateFlag(Integer id);

    List<ChatHistory> listAllMessage();

    List<ChatHistory> listNeedExportIds(List<Integer> needExportIds);

    List<Map<String, String>> listAllUser();
}
