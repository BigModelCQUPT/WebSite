package com.bigModel.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActiveAccount {
    private String username; // 用户名
    private Integer tweetCnt; // 一周的推文数量
}
