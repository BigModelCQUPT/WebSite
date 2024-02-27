package com.bigModel.backend.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.taobao.api.ApiException;
import okhttp3.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DingTalkNoticeUtil {
    public static void sendNotice() throws Exception {
        String accessToken = getAccessToken();
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setAgentId(2943425497L);
        // request.setUseridList("260665454821659594, 0233530551481796729358, 284563571837719894");
        request.setUseridList("01402302401219340");
        // request.setUseridList("260665454821659594");
        request.setToAllUser(false);

        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype("text");
        msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
        Map<String, String> content = new HashMap<>();
        content.put("发帖内容", "中华人民共和国");
        content.put("发帖ID", "112212121212");
        content.put("发帖时间", "2020-1-1 12:12:12");
        content.put("发帖链接", "https://baidu.com");
        content.put("风险提醒原因", "关键词匹配");
        String result = content.entrySet().stream()
                        .map(entry -> entry.getKey() + " : " + entry.getValue())
                                .collect(Collectors.joining(", \n"));
        msg.getText().setContent(result);
        request.setMsg(msg);


        // msg.setActionCard(new OapiMessageCorpconversationAsyncsendV2Request.ActionCard());
        // msg.getActionCard().setTitle("标题");
        // msg.getActionCard().setMarkdown("Markdown");
        // msg.getActionCard().setSingleTitle("SingleTitle");
        // msg.getActionCard().setSingleUrl("https://www.baidu.com");
        // msg.setMsgtype("action_card");
        // request.setMsg(msg);
        OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(request, accessToken);
        System.out.println(rsp.getBody());
    }
     public static String getAccessToken() throws Exception {
         OkHttpClient client = new OkHttpClient().newBuilder()
                 .build();
         Request request = new Request.Builder()
                 .url("https://oapi.dingtalk.com/gettoken?appkey=dingjw2oivs8xtepyvjr&appsecret=3_UZOIFFNKtadcqKJwehmDhUp6iSEOY8vSoj2IIIW17RQf27iSPYwhtPeCRIjTGM")
                 .method("GET", null)
                 .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                 .build();
         Response response = client.newCall(request).execute();
         ResponseBody res = response.body();
         JSONObject json = new JSONObject(res.string());
         return json.getString("access_token");
     }
    @Scheduled(cron = "0/5 * * * * ?") // 定时 5秒
    public void xxx() throws Exception {
        DingTalkNoticeUtil.sendNotice();
    }
}
