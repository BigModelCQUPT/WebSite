package com.bigModel.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Keyword {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String keyword;
    private Integer number;
}
