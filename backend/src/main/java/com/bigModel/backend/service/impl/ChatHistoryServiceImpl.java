package com.bigModel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bigModel.backend.mapper.ChatHistoryMapper;
import com.bigModel.backend.mapper.UserMapper;
import com.bigModel.backend.pojo.ChatHistory;
import com.bigModel.backend.service.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatHistoryServiceImpl extends ServiceImpl<ChatHistoryMapper, ChatHistory> implements ChatHistoryService {

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

    /**
     * 返回对应群组id的所有聊天记录
     * @return {@link List}<{@link ChatHistory}>
     */
    @Override
    public List<ChatHistory> listHistoryByGroupId(String groupId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("group_id", groupId);
        queryWrapper.orderBy(true, true, "group_id");
        return chatHistoryMapper.selectList(queryWrapper);
    }

    /**
     * 返回所有群组的id
     * @return {@link List}<{@link String}>
     */
    @Override
    public List<ChatHistory> listAllGroupId() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.groupBy("group_id");
        queryWrapper.select("group_id");
        return chatHistoryMapper.selectList(queryWrapper);
    }

    /**
     * 存入聊天记录
     * @param chatHistories
     */
    @Override
    public void saveHistories(List<ChatHistory> chatHistories) {
        this.saveBatch(chatHistories);
    }

    @Override
    public void updateFlag(Integer id) {
        ChatHistory chatHistory = new ChatHistory();
        chatHistory.setId(id);
        chatHistory.setFlag(1);
        chatHistoryMapper.updateById(chatHistory);
    }

    @Override
    public List<ChatHistory> listAllMessage() {
        QueryWrapper<ChatHistory> queryWrapper = new QueryWrapper<>();
        List<ChatHistory> chatHistoryList = chatHistoryMapper.selectList(queryWrapper);
        return chatHistoryList;
    }

    @Override
    public List<ChatHistory> listNeedExportIds(List<Integer> needExportIds) {
        QueryWrapper<ChatHistory> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", needExportIds);
        List<ChatHistory> chatHistoryList = chatHistoryMapper.selectList(queryWrapper);
        return chatHistoryList;
    }
}
