package com.bigModel.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatHistory {
    private Integer id;
    private String nickname;
    private String username;
    private String userId;
    private Date postalTime;
    private String message;
    private String groupName;
    private String groupId;
}
