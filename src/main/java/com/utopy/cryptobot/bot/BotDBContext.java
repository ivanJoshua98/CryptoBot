package com.utopy.cryptobot.bot;

import org.mapdb.DBMaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.db.MapDBContext;

@Configuration
public class BotDBContext {

    @Bean
    public DBContext dbContext() {
        return new MapDBContext(DBMaker
                .memoryDB()
                .closeOnJvmShutdown()
                .transactionEnable()
                .fileDeleteAfterClose()
                .make());
    }

}