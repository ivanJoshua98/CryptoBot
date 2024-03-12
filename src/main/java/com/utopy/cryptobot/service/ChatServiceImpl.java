package com.utopy.cryptobot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utopy.cryptobot.model.AppException;
import com.utopy.cryptobot.model.Chat;
import com.utopy.cryptobot.persistence.ChatRespository;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    private ChatRespository chatRespository;

	@Override
	public Chat saveChat(Chat newChat) {
		return this.chatRespository.save(newChat);
	}

	@Override
	public Chat getChatByID(Long chatID) {
		return this.chatRespository.findById(chatID).orElseThrow(() -> new AppException("Chat not found by id " + chatID.toString()));
	}

	@Override
	public List<Chat> getAllChats() {
		return this.chatRespository.findAll();
	}

	@Override
	public void deleteChat(Long chatID) {
		this.chatRespository.deleteById(chatID);
	}

}
