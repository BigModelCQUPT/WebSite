package com.bigModel.backend.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.taobao.api.ApiException;
import okhttp3.*;
import org.springframework.stereotype.Component;
import twitter4j.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DingTalkNoticeUtil {
    public static void sendNotice() throws Exception {
        String accessToken = getAccessToken();
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setAgentId(2832368212L);
        request.setUseridList("260665454821659594, 0233530551481796729358, 284563571837719894");
        request.setToAllUser(false);

        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype("text");
        msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = df.format(now);
        msg.getText().setContent("发现风险信息" + date);
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
                 .url("https://oapi.dingtalk.com/gettoken?appkey=dingksexqekhvsnkpzky&appsecret=QxsgKcezvtCqCcEytHXl4wL6l0sJ9yY12cZ6Ua9rCpDursrg2bXZjgpfJ220c7v1")
                 .method("GET", null)
                 .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                 .build();
         Response response = client.newCall(request).execute();
         ResponseBody res = response.body();
         JSONObject json = new JSONObject(res.string());
         return json.getString("access_token");
     }

}
