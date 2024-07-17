package com.giao_thong.model.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatStorage {
    public static final Map<String, List<ChatMessage>> messages = new HashMap<>();

    public static void saveMessage(ChatMessage chatMessage,String key) {
        messages.computeIfAbsent(key, k -> new ArrayList<>()).add(chatMessage);
    }

    public static List<ChatMessage> getMessages(String sender) {
        return messages.getOrDefault(sender, new ArrayList<>());
    }
}
