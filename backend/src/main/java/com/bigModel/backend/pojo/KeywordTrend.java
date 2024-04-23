package com.bigModel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("keyword_date")
public class KeywordTrend {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String forwardKeyword;
    private String backwardKeyword;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date nowDate;
    private Integer cnt;
}
