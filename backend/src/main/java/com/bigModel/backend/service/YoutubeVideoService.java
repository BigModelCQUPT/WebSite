package com.bigModel.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.pojo.YoutubeVideo;

import java.util.List;

public interface YoutubeVideoService {
    void addVideo(YoutubeVideo youtubeVideo);
    IPage<YoutubeVideo> listAll(int page, int size);
}
