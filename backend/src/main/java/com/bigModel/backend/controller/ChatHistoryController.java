package com.bigModel.backend.controller;

import com.bigModel.backend.advice.result.Result;
import com.bigModel.backend.pojo.ChatHistory;
import com.bigModel.backend.service.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("telegram")
public class ChatHistoryController {
    @Autowired
    private ChatHistoryService chatHistoryService;

    @PostMapping("/findByPage/{page}/{size}")
    public Result findTweetByPage(
            @PathVariable(value = "page") Integer page,
            @PathVariable(value = "size") Integer size) throws UnsupportedEncodingException, URISyntaxException {

        return Result.success(chatHistoryService.findByPage(page, size));
    }

    @GetMapping("export")
    public String exportHistory(HttpServletResponse httpServletResponse) {
        // Map<String, List<ChatHistory>> map = chatHistoryService.findAllHistory();
        httpServletResponse.setContentType("application/vnd.ms-excel");
        httpServletResponse.setHeader("Content-Disposition", "attachment; filename=" + "电报聊天记录.xlsx");
        //EasyExcel.write(httpServletResponse.getOutputStream())
        //        .head(getHeader());
        // toExcel.writeExcel(httpServletResponse, map);
        return "导出成功";
    }
}
