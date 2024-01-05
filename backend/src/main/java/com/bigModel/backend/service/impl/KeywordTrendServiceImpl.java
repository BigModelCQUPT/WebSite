package com.bigModel.backend.service.impl;

import com.bigModel.backend.mapper.KeywordTrendMapper;
import com.bigModel.backend.pojo.KeywordTrend;
import com.bigModel.backend.service.KeywordTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordTrendServiceImpl implements KeywordTrendService {

    @Autowired
    private KeywordTrendMapper keywordTrendMapper;

    /**
     * 当日最热关键词前五
     * @return {@link List}<{@link KeywordTrend}>
     */
    @Override
    public List<KeywordTrend> top5KeywordByCnt() {
        return keywordTrendMapper.top5KeywordByCnt();
    }

    /**
     * 根据传入的关键词返回当前关键词最近一周的变化趋势
     * @param keyword
     * @return {@link List}<{@link KeywordTrend}>
     */
    @Override
    public List<KeywordTrend> weekTrendByKeyword(String keyword) {
        return keywordTrendMapper.weekTrendByKeyword(keyword);
    }
}
