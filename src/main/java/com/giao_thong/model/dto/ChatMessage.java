package com.giao_thong.model.dto;

import lombok.Data;

@Data
public class ChatMessage {
    private String sender;
    private String recipient;
    private String content;
}

