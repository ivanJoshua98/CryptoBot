package com.utopy.cryptobot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.utopy.cryptobot.model.Crypto;

@SpringBootTest
public class CryptoServiceTest {

    private Crypto anyCrypto = new Crypto();

    @Autowired
    private CryptoService cryptoService;
    
    @Test
    @Transactional
    void testSaveAndGetCryptoBySymbol() {
        anyCrypto.setSymbol("ETHUSDT");
        this.cryptoService.saveCrypto(anyCrypto);

        Crypto storedCrypto = cryptoService.getCryptoBySymbol(anyCrypto.getSymbol());

        assertEquals(storedCrypto.getSymbol(), anyCrypto.getSymbol());

    }
}
