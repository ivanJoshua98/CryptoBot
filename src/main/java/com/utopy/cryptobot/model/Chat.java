package com.utopy.cryptobot.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Chat {

    @Id
    private Long chatID;

    public Chat(){
        super();
    }

    public Chat(Long chatID){
        this.chatID = chatID;
    }

	public Long getChatID() {
		return chatID;
	}

}
