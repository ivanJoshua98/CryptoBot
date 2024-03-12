package com.utopy.cryptobot.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.utopy.cryptobot.model.Crypto;
import com.utopy.cryptobot.model.CryptoEnum;
import com.utopy.cryptobot.model.USDTtoARS;

@Component
@Service
public class BinanceProxyService {

    @Autowired
    private CryptoService cryptoService;

    @Value("${integration.binance.api.url:NONE}")
	private String binanceApiURL;

	private String criptoyaApiURL = "https://criptoya.com/api/binance/";

    private RestTemplate restTemplate = new RestTemplate();

    public Crypto getCryptoValue(String symbol) {
        return restTemplate.getForObject(binanceApiURL + "ticker/price?symbol=" + symbol, Crypto.class);
	}

    public USDTtoARS getUSDTtoARS() {
        return restTemplate.getForObject(criptoyaApiURL + "usdt/ars/0.1", USDTtoARS.class);
    }

    @Scheduled(cron = "${quotation.cron.expression:NONE}")
	public void getAndSaveAllCryptos() {
		for (CryptoEnum crypto : CryptoEnum.values()) {
			Crypto entity = getCryptoValue(crypto.name());		
            USDTtoARS ars = getUSDTtoARS();
			if (entity != null) {
                entity.setArsPrice(ars.getAsk() * entity.getPrice());
                this.cryptoService.saveCrypto(entity);
            }
        }
    }

}
