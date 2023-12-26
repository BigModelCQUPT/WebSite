package com.bigModel.backend.task;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bigModel.backend.pojo.Keyword;
import com.bigModel.backend.pojo.Tweet;
import com.bigModel.backend.pojo.YoutubeUser;
import com.bigModel.backend.pojo.YoutubeVideo;
import com.bigModel.backend.service.KeywordService;
import com.bigModel.backend.service.YoutubeUserService;
import com.bigModel.backend.service.YoutubeVideoService;
import com.bigModel.backend.utils.YoutubeVideoUtil;
import com.bigModel.backend.utils.bibiGPT;
import com.bigModel.backend.utils.chatGPT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class YoutubeTask {

    @Autowired
    private YoutubeVideoService youtubeVideoService;

    @Autowired
    private YoutubeUserService youtubeUserService;

    @Autowired
    private KeywordService keywordService;

    @Scheduled(cron = "0 0 7 * * ?")
    @Transactional
    public void VideoSummary() throws Exception{

        List<YoutubeUser> userList = youtubeUserService.listAll();
        List<YoutubeVideo> list = YoutubeVideoUtil.getVideo(userList);
        for (int i = 0; i < list.size(); i ++) {
            youtubeVideoService.addVideo(list.get(i));
        }

        List<YoutubeVideo> listAllVideo = youtubeVideoService.listAllVideo();
        for(int i = 0;i < listAllVideo.size();i++){
            YoutubeVideo youtubeVideo = listAllVideo.get(i);
            String url = listAllVideo.get(i).getVideoUrl();
            String summary = bibiGPT.getSummary(url);
            System.out.println(summary);
            youtubeVideo.setSummary(summary);
            youtubeVideoService.saveSummary(youtubeVideo);
        }
        this.keywordMatch();
        this.useChatGPT();
    }

    public void keywordMatch() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = df.format(now);
        List<Keyword> keywordList = keywordService.listAllKeywords();
        List<YoutubeVideo> youtubeVideoList = youtubeVideoService.listAllVideo();
        for (int i = 0; i < youtubeVideoList.size(); i++) {
            List<String> list = new ArrayList<>();
            int id = youtubeVideoList.get(i).getId();
            for (int j = 0; j < keywordList.size(); j++) {
                String keyword = keywordList.get(j).getKeyword();
                if (youtubeVideoService.checkKeyword(id, keyword)) {
                    list.add(keyword);
                }
            }
            if (list.size() > 0) {
                youtubeVideoService.updateReturn(id);
                youtubeVideoService.saveKeywordList(id, list);
            }
        }
    }

    public void useChatGPT(){
        List<YoutubeVideo> youtubeVideoList = youtubeVideoService.listAllNoReturn();
        for(int i = 0;i < youtubeVideoList.size();i++){
            int id = youtubeVideoList.get(i).getId();
            String summary = youtubeVideoList.get(i).getSummary();
            HashMap<String, Object> answer = chatGPT.getAnswer(summary);
            String category = answer.get("category").toString();
            if(!category.equals("无类别") || !category.equals("无法分类")){
                youtubeVideoService.updateReturn(id);
            }
        }
    }
}
