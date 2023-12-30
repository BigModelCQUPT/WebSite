package com.bigModel.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TopicKeyword {
//    根据前端图标定制 测试过后 必须是这两个属性名字 否则前端需要重新映射
    private Integer value;
    private String name;
}
