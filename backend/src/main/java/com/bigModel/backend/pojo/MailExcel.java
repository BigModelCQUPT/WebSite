package com.bigModel.backend.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.ContentStyle;
import com.alibaba.excel.metadata.data.WriteCellData;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 邮件的附件excel的实体
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ContentRowHeight(100)
public class MailExcel {
    @ExcelProperty("推文id")
    private String tweetid;
    @ExcelProperty("推文内容")
    private String text;
    @ExcelProperty("用户名")
    private String username;
    @ExcelProperty("推文地址")
    private String url;
    @ExcelProperty("发布时间")
    @DateTimeFormat(fallbackPatterns = "YYYY-MM-DD HH:MM:SS")
    private Date publishTime;
    @ExcelProperty("图片")
    // private String image;
    private WriteCellData<Void> writeCellData;
    @ExcelProperty("视频")
    private String video;
}
