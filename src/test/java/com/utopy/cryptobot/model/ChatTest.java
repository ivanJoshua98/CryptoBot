package com.utopy.cryptobot.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ChatTest {
    @Test
    void testGetChatID() {
        Chat newChat = new Chat(1111111L);

        assertEquals(newChat.getChatID(), 1111111L);
    }
}
