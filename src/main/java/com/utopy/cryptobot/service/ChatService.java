package com.utopy.cryptobot.service;

import java.util.List;

import com.utopy.cryptobot.model.Chat;

public interface ChatService {

    Chat saveChat(Chat newUser);

    Chat getChatByID(Long chatID);

    List<Chat> getAllChats();

    void deleteChat(Long chatID);
}
