package com.utopy.cryptobot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.utopy.cryptobot.model.Crypto;
import com.utopy.cryptobot.model.CryptoEnum;
import com.utopy.cryptobot.model.USDTtoARS;

@SpringBootTest
public class BinanceProxyServiceTest {

    @Autowired
    private BinanceProxyService binanceProxyService;

    @Test
    void testGetCryptoValue() {
        Crypto cryptoBinance = this.binanceProxyService.getCryptoValue(CryptoEnum.WLDUSDT.toString());

        assertEquals(cryptoBinance.getSymbol(), CryptoEnum.WLDUSDT.toString());
        assertNotNull(cryptoBinance.getPrice());

    }

    @Test
    void testGetUSDTtoARS() {
        USDTtoARS usdtTOARS = this.binanceProxyService.getUSDTtoARS();
        
        assertNotNull(usdtTOARS.getAsk());
        assertNotNull(usdtTOARS.getBid());
        assertNotNull(usdtTOARS.getTime());
        assertNotNull(usdtTOARS.getTotalAsk());
        assertNotNull(usdtTOARS.getTotalBid());

    }
}
