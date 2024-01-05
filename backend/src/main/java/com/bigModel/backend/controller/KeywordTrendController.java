package com.bigModel.backend.controller;

import com.bigModel.backend.pojo.KeywordTrend;
import com.bigModel.backend.service.KeywordTrendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("keyword")
public class KeywordTrendController {

    @Autowired
    private KeywordTrendService keywordTrendService;

    @GetMapping("trend")
    public Map<String, Object> keyTrend() {
        List<KeywordTrend> keywordTrendList = keywordTrendService.top5KeywordByCnt();
        Map<String, Object> ansKeywordList = new HashMap<>();
        List<String> keywordList = new ArrayList<>();
        for (KeywordTrend keywordTrend : keywordTrendList) {
            keywordList.add(keywordTrend.getKeyword());
        }
        ansKeywordList.put("keywords", keywordList);
        Map<String, List<KeywordTrend>> keyTrend = new HashMap<>();

        for (String keyword: keywordList) {
            List<KeywordTrend> list = keywordTrendService.weekTrendByKeyword(keyword);
            keyTrend.put(keyword, list);
        }
        ansKeywordList.put("trend", keyTrend);
        return ansKeywordList;
    }
}
