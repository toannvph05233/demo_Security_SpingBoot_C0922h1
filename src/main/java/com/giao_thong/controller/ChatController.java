
package com.giao_thong.controller;

import com.giao_thong.model.dto.ChatMessage;
import com.giao_thong.model.dto.ChatStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/register")
    public void register(@Payload ChatMessage chatMessage) {
        String username = chatMessage.getSender();
        ChatStorage.messages.put(username, new ArrayList<>());
        messagingTemplate.convertAndSend("/topic/user-list", ChatStorage.messages);
    }

    @MessageMapping("/user/chat")
    public void userChat(@Payload ChatMessage chatMessage) {
        ChatStorage.saveMessage(chatMessage, chatMessage.getSender());
        messagingTemplate.convertAndSend("/topic/user-list", ChatStorage.messages);
    }

    @MessageMapping("/admin/chat")
    public void adminChat(@Payload ChatMessage chatMessage) {
        ChatStorage.saveMessage(chatMessage, chatMessage.getRecipient());
        messagingTemplate.convertAndSend("/topic/user-list", ChatStorage.messages);
    }

//    @GetMapping("/chat-messages/{recipient}")
//    @ResponseBody
//    public List<ChatMessage> getMessages(@PathVariable String recipient) {
//        return ChatStorage.getMessages(recipient);
//    }
}
