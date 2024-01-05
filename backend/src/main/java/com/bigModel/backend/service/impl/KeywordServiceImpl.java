package com.bigModel.backend.service.impl;

import com.bigModel.backend.mapper.KeywordMapper;
import com.bigModel.backend.mapper.TweetMapper;
import com.bigModel.backend.pojo.Keyword;
import com.bigModel.backend.pojo.KeywordTrend;
import com.bigModel.backend.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordServiceImpl implements KeywordService {
    @Autowired
    private KeywordMapper keywordMapper;

    @Override
    public void addKeyword(Keyword keyword) {
        keywordMapper.insert(keyword);
    }

    @Override
    public void deleteKeyword(Integer id) {
        keywordMapper.deleteById(id);
    }

    @Override
    public List<Keyword> listAllKeywords() {
        List<Keyword> list = keywordMapper.selectList(null);
        return list;
    }

    @Override
    public void updateKeywordNumber(Keyword keyword) {
        Integer number = keyword.getNumber();
        number = number + 1;
        keyword.setNumber(number);
        keywordMapper.updateById(keyword);
    }

    @Override
    public List<KeywordTrend> listAllKeywordWithDate() {
        return keywordMapper.listAllKeyword();
    }
}
