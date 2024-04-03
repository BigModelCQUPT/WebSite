package com.bigModel.backend.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.request.OapiMessageSendToConversationRequest;
import com.dingtalk.api.request.OapiRobotSendRequest;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.dingtalk.api.response.OapiMessageSendToConversationResponse;
import com.dingtalk.api.response.OapiRobotSendResponse;
import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.taobao.api.ApiException;
import okhttp3.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.JSONObject;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DingTalkNoticeUtil {
    // public static void sendNotice() throws Exception {
    //     String accessToken = getAccessToken();
    //     DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
    //     OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
    //     // request.setAgentId(2943425497L);
    //     request.setAgentId(2832368212L);
    //     // request.setUseridList("260665454821659594, 0233530551481796729358, 284563571837719894");
    //     request.setUseridList("01402302401219340");
    //     // request.setUseridList("260665454821659594");
    //     request.setToAllUser(false);
    //
    //     OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
    //     msg.setMsgtype("text");
    //     msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
    //     Map<String, String> content = new HashMap<>();
    //     content.put("发帖内容", "中华人民共和国");
    //     content.put("发帖ID", "112212121212");
    //     content.put("发帖时间", "2020-1-1 12:12:12");
    //     content.put("发帖链接", "https://baidu.com");
    //     content.put("风险提醒原因", "关键词匹配");
    //     String result = content.entrySet().stream()
    //                     .map(entry -> entry.getKey() + " : " + entry.getValue())
    //                             .collect(Collectors.joining(", \n"));
    //     msg.getText().setContent(result);
    //     request.setMsg(msg);
    //
    //
    //     // msg.setActionCard(new OapiMessageCorpconversationAsyncsendV2Request.ActionCard());
    //     // msg.getActionCard().setTitle("标题");
    //     // msg.getActionCard().setMarkdown("Markdown");
    //     // msg.getActionCard().setSingleTitle("SingleTitle");
    //     // msg.getActionCard().setSingleUrl("https://www.baidu.com");
    //     // msg.setMsgtype("action_card");
    //     // request.setMsg(msg);
    //     OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(request, accessToken);
    //     System.out.println(rsp.getBody());
    // }
     public static String getAccessToken() throws Exception {
         OkHttpClient client = new OkHttpClient().newBuilder()
                 .build();
         Request request = new Request.Builder()
                 // .url("https://oapi.dingtalk.com/gettoken?appkey=dingjw2oivs8xtepyvjr&appsecret=3_UZOIFFNKtadcqKJwehmDhUp6iSEOY8vSoj2IIIW17RQf27iSPYwhtPeCRIjTGM")
                 .url("https://oapi.dingtalk.com/gettoken?appkey=dingksexqekhvsnkpzky&appsecret=QxsgKcezvtCqCcEytHXl4wL6l0sJ9yY12cZ6Ua9rCpDursrg2bXZjgpfJ220c7v1")
                 .method("GET", null)
                 .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                 .build();
         Response response = client.newCall(request).execute();
         ResponseBody res = response.body();
         JSONObject json = new JSONObject(res.string());
         return json.getString("access_token");
     }
    public static void sendNotice(String content) throws Exception {
        Long timestamp = System.currentTimeMillis();
        String secret = "SECfabd23d8aec3a91625889f58f73a0fd3f451796541d19fde6f4dbcd9508eb787"; // 公司
        // String secret = "SEC1cdac38aaa6990e1aaca8f53f08c318f3b9fe7f5ccc5d59737e394120ecfc3a4"; // 自己

        String stringToSign = timestamp + "\n" + secret;
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(new SecretKeySpec(secret.getBytes("UTF-8"), "HmacSHA256"));
        byte[] signData = mac.doFinal(stringToSign.getBytes("UTF-8"));
        String sign = URLEncoder.encode(new String(Base64.encodeBase64(signData)),"UTF-8");
        // DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?sign="+sign+"&timestamp="+timestamp);
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/robot/send?sign="+sign+"&timestamp="+timestamp);
        OapiRobotSendRequest req = new OapiRobotSendRequest();
        OapiRobotSendRequest.Text text = new OapiRobotSendRequest.Text();
        text.setContent(content);
        req.setMsgtype("text");
        req.setText(text);
        OapiRobotSendResponse response = client.execute(req, "4991e18ad9bea3961fa1c34d7053983c043e85d3be3164920e4ca0a59e9c223a"); // 公司
        // OapiRobotSendResponse response = client.execute(req, "65d2e2fa5625554f1e67e9f87270a44a5e602647e6519e81fc52e729923a245a"); // 自己
        System.out.println(response.getBody());
    }
}
