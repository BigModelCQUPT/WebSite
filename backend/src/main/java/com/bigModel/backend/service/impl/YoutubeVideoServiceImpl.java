package com.bigModel.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bigModel.backend.mapper.YoutubeVideoMapper;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.pojo.YoutubeVideo;
import com.bigModel.backend.service.YoutubeVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YoutubeVideoServiceImpl implements YoutubeVideoService {

    @Autowired
    private YoutubeVideoMapper youtubeVideoMapper;
    @Override
    public void addVideo(YoutubeVideo youtubeVideo) {
        youtubeVideoMapper.insert(youtubeVideo);
    }

    @Override
    public IPage<YoutubeVideo> listAll(int pageNum, int size) {
        IPage<YoutubeVideo> page = new Page<>(pageNum, size);
        return youtubeVideoMapper.selectPage(page, null);
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
        queryWrapper.eq("need_return", 1);
        List<YoutubeVideo> youtubeVideoList = youtubeVideoMapper.selectList(queryWrapper);
        return youtubeVideoList;
    }
}
