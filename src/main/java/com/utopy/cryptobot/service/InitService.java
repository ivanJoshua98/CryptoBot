package com.utopy.cryptobot.service;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.annotation.PostConstruct;

@Service
public class InitService {

    protected final Log logger = LogFactory.getLog(getClass());

    @Value("${spring.datasource.driverClassName}")
	private String className;
	
	@Autowired
    private BinanceProxyService binanceProxyService;

	@PostConstruct
	public void initialize() {
		if (className.equals("org.postgresql.Driver")) {
			logger.warn("Init Data Using PostgreSQL DB");
			fireInitialData();
		}
	}	

    private void fireInitialData() {
		this.binanceProxyService.getAndSaveAllCryptos();
    }

}
