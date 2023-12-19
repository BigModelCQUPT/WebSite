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
}
