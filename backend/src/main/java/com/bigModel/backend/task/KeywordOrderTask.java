package com.bigModel.backend.task;

import com.bigModel.backend.pojo.KeywordTrend;
import com.bigModel.backend.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * 定时任务 更新存储每天的关键词频率
 */
@Component
public class KeywordOrderTask {

    @Autowired
    private KeywordService keywordService;

    @Scheduled(cron = "0/5 * * * * ?") // 定时 5秒
    public void updateKeywordCnt() {
        // Date
        List<KeywordTrend> list = keywordService.listAllKeywordWithDate();
        System.out.println(list.get(0).getNowDate());
    }
}
