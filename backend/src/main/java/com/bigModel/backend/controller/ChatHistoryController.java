package com.bigModel.backend.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.bigModel.backend.advice.result.Result;
import com.bigModel.backend.listener.UploadDataListener;
import com.bigModel.backend.pojo.ChatHistory;
import com.bigModel.backend.pojo.YoutubeVideo;
import com.bigModel.backend.service.ChatHistoryService;
import com.bigModel.backend.utils.ExcelExportUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("telegram")
@Slf4j
public class ChatHistoryController {
    @Autowired
    private ChatHistoryService chatHistoryService;

    @PostMapping("/findByPage/{page}/{size}")
    public Result findTweetByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size,
                    @RequestBody List<String> data) throws UnsupportedEncodingException, URISyntaxException {
        return Result.success(chatHistoryService.findByPage(page, size, data));
    }

    @GetMapping("export")
    public void exportHistory(@RequestParam(required = false) String ids, HttpServletResponse httpServletResponse) throws Exception {
        if(StringUtils.isNotBlank(ids)){
            List<Integer> needExportIds = Arrays.stream(ids.split(",")).map(Integer::valueOf).collect(Collectors.toList());
            List<ChatHistory> listMessage = chatHistoryService.listNeedExportIds(needExportIds);
            EasyExcelFactory.write(httpServletResponse.getOutputStream(), ChatHistory.class).sheet("telegram").doWrite(listMessage);
        }
        else{
            List<ChatHistory> list = chatHistoryService.listAllGroupId();
            Map<String, List<ChatHistory>> map = new HashMap<>();
            for (int i = 0; i < list.size(); i ++) {
                String groupId = list.get(i).getGroupId();
                List<ChatHistory> chatHistories = chatHistoryService.listHistoryByGroupId(groupId);
                map.put(groupId, chatHistories);
            }
            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + "text.xlsx");
            ExcelExportUtil.writeExcel(httpServletResponse, map);
        }
//
    }

    @PostMapping("upload")
    public void upload( MultipartFile file) throws IOException {
        EasyExcel.read(file.getInputStream(), ChatHistory.class, new UploadDataListener(chatHistoryService)).sheet().doRead();
    }

    @PostMapping("/readTelegram")
    public Result readTweet(@RequestBody List<ChatHistory> data){
//        List<Tweet> needReadList = castList(data.get("needReadList"), Tweet.class);
        for (ChatHistory chatHistory : data) {
            Integer id = chatHistory.getId();
            chatHistoryService.updateFlag(id);
        }
        return Result.success("修改成功");
    }

    @GetMapping("/findAllUser")
    public Result<List<Map<String, String>>> findAllUser() {
        List<Map<String, String>> telegramUserList = chatHistoryService.listAllUser();
        return Result.success(telegramUserList);
    }

}
