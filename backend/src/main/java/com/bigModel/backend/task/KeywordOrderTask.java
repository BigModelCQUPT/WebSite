package com.bigModel.backend.task;

import com.bigModel.backend.mapper.KeywordTrendMapper;
import com.bigModel.backend.pojo.KeywordTrend;
import com.bigModel.backend.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 定时任务 更新存储每天的关键词频率
 */
@Component
public class KeywordOrderTask {

    @Autowired
    private KeywordService keywordService;
    @Autowired
    private KeywordTrendMapper keywordTrendMapper;

    // @Scheduled(cron = "0/5 * * * * ?") // 定时 5秒
    @Scheduled(cron = "0 0 0 * * ?") // 定时 0 点
    public void updateKeywordCnt() {
        List<KeywordTrend> list = keywordService.listAllKeywordWithDate();
        for (KeywordTrend keywordTrend: list) {
            keywordTrendMapper.insert(keywordTrend);
        }
    }
}
