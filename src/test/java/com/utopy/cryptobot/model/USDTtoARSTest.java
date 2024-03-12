package com.utopy.cryptobot.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class USDTtoARSTest {

    private USDTtoARS usdtTOars = new USDTtoARS();

    @Test
    void testSetAndGetAsk() {
        usdtTOars.setAsk(120.0f);

        assertEquals(usdtTOars.getAsk(), 120.0f);
    }

    @Test
    void testSetAndGetBid() {
        usdtTOars.setBid(122.2f);

        assertEquals(usdtTOars.getBid(), 122.2f);
    }

    @Test
    void testSetAndGetTime() {
        usdtTOars.setTime(11032024);

        assertEquals(usdtTOars.getTime(), 11032024);
    }

    @Test
    void testSetAndGetTotalAsk() {
        usdtTOars.setTotalAsk(124.4f);

        assertEquals(usdtTOars.getTotalAsk(), 124.4f);
    }

    @Test
    void testSetAndGetTotalBid() {
        usdtTOars.setTotalBid(123.3f);

        assertEquals(usdtTOars.getTotalBid(), 123.3f);
    }
}
