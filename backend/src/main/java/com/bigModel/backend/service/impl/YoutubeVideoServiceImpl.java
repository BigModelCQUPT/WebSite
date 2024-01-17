package com.bigModel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigModel.backend.mapper.YoutubeVideoMapper;
import com.bigModel.backend.pojo.ChatHistory;
import com.bigModel.backend.pojo.YoutubeVideo;
import com.bigModel.backend.service.YoutubeVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class YoutubeVideoServiceImpl implements YoutubeVideoService {

    @Autowired
    private YoutubeVideoMapper youtubeVideoMapper;
    @Override
    public void addVideo(YoutubeVideo youtubeVideo) {
        youtubeVideoMapper.insert(youtubeVideo);
    }

    @Override
    public IPage<YoutubeVideo> listAll(int pageNum, int size, List<String> needName) {
        IPage<YoutubeVideo> page = new Page<>(pageNum, size);
        QueryWrapper<YoutubeVideo> queryWrapper = new QueryWrapper<>();
        if(!needName.isEmpty() && needName != null){
            queryWrapper.in("username", needName);
        }
        queryWrapper.orderByDesc("id");
        return youtubeVideoMapper.selectPage(page, queryWrapper);
    }


    @Override
    public List<YoutubeVideo> listAllVideo() {
        QueryWrapper<YoutubeVideo> queryWrapper = new QueryWrapper<>();
        return youtubeVideoMapper.selectList(queryWrapper);
    }

    @Override
    public void saveSummary(YoutubeVideo youtubeVideo) {
        youtubeVideoMapper.updateById(youtubeVideo);
    }

    @Override
    public boolean checkKeyword(int id, String keyword) {
        return youtubeVideoMapper.checkKeyword(id, keyword) == 1;
    }

    @Override
    public void updateReturn(int id) {
        YoutubeVideo youtubeVideo = new YoutubeVideo();
        youtubeVideo.setId(id);
        youtubeVideo.setNeedReturn(1);
        youtubeVideoMapper.updateById(youtubeVideo);
    }

    @Override
    public void saveKeywordList(int id, List<String> list) {
        String join = String.join(",", list);
        YoutubeVideo youtubeVideo = youtubeVideoMapper.selectById(id);
        youtubeVideo.setKeyword(join);
        youtubeVideoMapper.updateById(youtubeVideo);
    }

    @Override
    public List<YoutubeVideo> listAllNoReturn() {
        QueryWrapper<YoutubeVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("need_return", 0);
        List<YoutubeVideo> youtubeVideoList = youtubeVideoMapper.selectList(queryWrapper);
        return youtubeVideoList;
    }

    @Override
    public void updateFlag(Integer id) {
        YoutubeVideo youtubeVideo = new YoutubeVideo();
        youtubeVideo.setId(id);
        youtubeVideo.setFlag(1);
        youtubeVideoMapper.updateById(youtubeVideo);
    }

    @Override
    public List<YoutubeVideo> listNeedExportIds(List<Integer> needExportIds) {
        QueryWrapper<YoutubeVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", needExportIds);
        List<YoutubeVideo> youtubeVideoList = youtubeVideoMapper.selectList(queryWrapper);
        return youtubeVideoList;
    }

    @Override
    public List<YoutubeVideo> listAllExportIds() {
        QueryWrapper<YoutubeVideo> queryWrapper = new QueryWrapper<>();
        List<YoutubeVideo> youtubeVideoList = youtubeVideoMapper.selectList(queryWrapper);
        return youtubeVideoList;
    }

    @Override
    public List<Map<String, String>> listAllUser() {
        List<Map<String, String>> res = new ArrayList<>();
        QueryWrapper<YoutubeVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username").groupBy("username");
        List<YoutubeVideo> chatHistoryList = youtubeVideoMapper.selectList(queryWrapper);
        for(YoutubeVideo youtubeVideo : chatHistoryList){
            Map<String, String> userMap = new HashMap<>();
            userMap.put("label", youtubeVideo.getUsername());
            userMap.put("key", youtubeVideo.getUsername());
            res.add(userMap);
        }
        return res;
    }

    @Override
    public boolean alreadyAbsent(YoutubeVideo youtubeVideo) {
        QueryWrapper<YoutubeVideo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("video_url", youtubeVideo.getVideoUrl());
        YoutubeVideo selectById = youtubeVideoMapper.selectOne(queryWrapper);
        return selectById != null;
    }

    @Override
    public void updateReason(int id, String res) {
        YoutubeVideo youtubeVideo = youtubeVideoMapper.selectById(id);
        youtubeVideo.setFeedbackReason(res);
        youtubeVideoMapper.updateById(youtubeVideo);
    }
}
