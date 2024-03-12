package com.utopy.cryptobot.service;

import com.utopy.cryptobot.model.Crypto;

public interface CryptoService {

    Crypto saveCrypto(Crypto crypto);
    
    Crypto getCryptoBySymbol(String symbol);

}
