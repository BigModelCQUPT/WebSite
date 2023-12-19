package com.bigModel.backend.utils;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.DingTalkClient;
import com.dingtalk.api.request.OapiMessageCorpconversationAsyncsendV2Request;
import com.dingtalk.api.response.OapiMessageCorpconversationAsyncsendV2Response;
import com.taobao.api.ApiException;
import org.springframework.stereotype.Component;

@Component
public class DingTalkNoticeUtil {
    public static void sendNotice() throws ApiException {
        DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/message/corpconversation/asyncsend_v2");
        OapiMessageCorpconversationAsyncsendV2Request request = new OapiMessageCorpconversationAsyncsendV2Request();
        request.setAgentId(2832368212L);
        request.setUseridList("260665454821659594");
        request.setToAllUser(false);

        OapiMessageCorpconversationAsyncsendV2Request.Msg msg = new OapiMessageCorpconversationAsyncsendV2Request.Msg();
        msg.setMsgtype("text");
        msg.setText(new OapiMessageCorpconversationAsyncsendV2Request.Text());
        msg.getText().setContent("发现风险信息");
        request.setMsg(msg);


        // msg.setActionCard(new OapiMessageCorpconversationAsyncsendV2Request.ActionCard());
        // msg.getActionCard().setTitle("标题");
        // msg.getActionCard().setMarkdown("Markdown");
        // msg.getActionCard().setSingleTitle("SingleTitle");
        // msg.getActionCard().setSingleUrl("https://www.baidu.com");
        // msg.setMsgtype("action_card");
        // request.setMsg(msg);
        OapiMessageCorpconversationAsyncsendV2Response rsp = client.execute(request, "9d642f2051673c8899050ead24e1cab5");
        System.out.println(rsp.getBody());
    }
}
