package com.bigModel.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.pojo.YoutubeVideo;

import java.util.List;
import java.util.Map;

public interface YoutubeVideoService {
    void addVideo(YoutubeVideo youtubeVideo);
    IPage<YoutubeVideo> listAll(int page, int size, List<String> data);

    List<YoutubeVideo> listAllVideo();

    void saveSummary(YoutubeVideo youtubeVideo);

    boolean checkKeyword(int id, String keyword);

    void updateReturn(int id);

    void saveKeywordList(int id, List<String> list);

    List<YoutubeVideo> listAllNoReturn();

    void updateFlag(Integer id);

    List<YoutubeVideo> listNeedExportIds(List<Integer> needExportIds);

    List<YoutubeVideo> listAllExportIds();

    List<Map<String, String>> listAllUser();

    boolean alreadyAbsent(YoutubeVideo youtubeVideo);

    void updateReason(int id, String res);
}
