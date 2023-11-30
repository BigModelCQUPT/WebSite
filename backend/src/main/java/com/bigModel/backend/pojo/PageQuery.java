package com.bigModel.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // 自动添加构造函数
@NoArgsConstructor
@AllArgsConstructor
public class PageQuery {
    private Integer currentNum; // 当前页
    private Integer pageSize; // 每页显示数量
}
