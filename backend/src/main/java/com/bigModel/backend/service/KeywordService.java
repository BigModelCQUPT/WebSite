package com.bigModel.backend.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.pojo.Keyword;

import java.util.List;

public interface KeywordService {

    void addKeyword(Keyword keyword);
    void deleteKeyword(Integer id);
    List<Keyword> listAllKeywords();
}
