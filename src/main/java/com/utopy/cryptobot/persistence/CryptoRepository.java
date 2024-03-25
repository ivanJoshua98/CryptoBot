package com.utopy.cryptobot.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.utopy.cryptobot.model.Crypto;

@Repository
public interface CryptoRepository extends JpaRepository<Crypto, String>{

}
