package com.utopy.cryptobot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.utopy.cryptobot.model.AppException;
import com.utopy.cryptobot.model.Chat;

@SpringBootTest
public class ChatServiceTest {

    @Autowired
    private ChatService chatService;

    private Chat anyChat = new Chat(1111111L);

    @Test
    @Transactional
    void testSaveAndGetChatByID() {
        chatService.saveChat(anyChat);

        Chat storedChat = chatService.getChatByID(anyChat.getChatID());

        assertEquals(storedChat.getChatID(), anyChat.getChatID());

    }

    @Test
    @Transactional
    void testSaveAndGetAllChats() {
        chatService.saveChat(anyChat);

        assertTrue(chatService.getAllChats()
                              .stream()
                              .map( c -> c.getChatID())
                              .toList()
                              .contains(anyChat.getChatID()));

    }


    @Test
    @Transactional
    void testSaveAndDeleteChat() {
        chatService.saveChat(anyChat);

        chatService.deleteChat(anyChat.getChatID());

        assertThrows(AppException.class,
            ()->{
            chatService.getChatByID(anyChat.getChatID());
            });
    }
}
