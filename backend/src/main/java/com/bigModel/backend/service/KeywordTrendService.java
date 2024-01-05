package com.bigModel.backend.service;

import com.bigModel.backend.pojo.KeywordTrend;

import java.util.List;

public interface KeywordTrendService {
    List<KeywordTrend> top5KeywordByCnt();
    List<KeywordTrend> weekTrendByKeyword(String keyword);
}
