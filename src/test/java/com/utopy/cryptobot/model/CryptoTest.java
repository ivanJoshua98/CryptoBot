package com.utopy.cryptobot.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CryptoTest {

    private Crypto anyCrypto = new Crypto();

    @Test
    void testSetAndGetArsPrice() {
        anyCrypto.setArsPrice(1000.00);

        assertEquals(anyCrypto.getArsPrice(), 1000.00);
    }

    @Test
    void testSetAndGetSymbol() {
        anyCrypto.setSymbol("BTCUSDT");

        assertEquals(anyCrypto.getSymbol(), "BTCUSDT");
    }


    @Test
    void testSetAndGetPrice() {
        anyCrypto.setPrice(200.00);

        assertEquals(anyCrypto.getPrice(), 200.00);

    }

}
