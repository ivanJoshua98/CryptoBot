package com.utopy.cryptobot.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utopy.cryptobot.model.Chat;

@Repository
public interface ChatRespository extends JpaRepository<Chat, Long>{

}
