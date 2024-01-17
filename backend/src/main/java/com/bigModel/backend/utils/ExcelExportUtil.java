package com.bigModel.backend.utils;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.bigModel.backend.pojo.ChatHistory;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 导出excel：电报聊天记录
 */
public class ExcelExportUtil {

    public static void writeExcel(HttpServletResponse httpServletResponse, Map<String, List<ChatHistory>> map) throws Exception {
        ExcelWriter excelWriter = EasyExcel.write(httpServletResponse.getOutputStream()).build();
        int i = 0;

        for (String key: map.keySet()) {
            WriteSheet sheet = EasyExcel.writerSheet(i, "sheet" + i).head(ChatHistory.class).build();
            excelWriter.write(map.get(key), sheet);
            i ++;
        }
        excelWriter.finish();
    }
}
