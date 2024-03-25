package com.utopy.cryptobot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utopy.cryptobot.model.AppException;
import com.utopy.cryptobot.model.Crypto;
import com.utopy.cryptobot.persistence.CryptoRepository;

@Service
public class CryptoServiceImpl implements CryptoService{

    @Autowired
    CryptoRepository cryptoRepository;

    @Override
    public Crypto saveCrypto(Crypto crypto) {
        return this.cryptoRepository.save(crypto);
    }

    @Override
    public Crypto getCryptoBySymbol(String symbol) {
        return this.cryptoRepository.findById(symbol).orElseThrow(() -> new AppException("Crypto not found by symbol " + symbol));
    }

}
