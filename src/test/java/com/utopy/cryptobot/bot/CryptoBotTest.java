package com.utopy.cryptobot.bot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.After;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;

import com.utopy.cryptobot.model.Crypto;
import com.utopy.cryptobot.model.CryptoEnum;
import com.utopy.cryptobot.service.CryptoService;


@SpringBootTest
public class CryptoBotTest {

    @Autowired
    private DBContext dbContext;

    @Autowired
    private CryptoBot cryptoBot;

    @Autowired
    private CryptoService cryptoService;

    @Mock
    private SilentSender silent;

    private ElementBuilderBotTest builder;


    @BeforeEach
    void setUp() {
        //Initialize abilities
        cryptoBot.onRegister();

        cryptoBot.setSilentSender(silent);

        builder = new ElementBuilderBotTest();
    }

    @After
    public void tearDown() throws Exception {
        dbContext.clear();
        dbContext.close();
    }

    @Test
    void whenStartComandIsInvoked_thenItAnswersHolaAndShowsButtonsCotizacionActualAndCancelarMensajes() {
        //Incoming update
        Update update = builder.anyUpdateWithText("/start");

        cryptoBot.onUpdateReceived(update);

        SendMessage sendMessage = builder.expectedMessageWithText("¡Hola! \nTe notificaré periodicamente la cotización de Worldcoin");

        ReplyKeyboardMarkup replyKeyboardMarkup = builder.twoButtonsWithText("Cancelar mensajes", "Cotizacion actual");
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        sendMessage.setParseMode(ParseMode.MARKDOWN);

        Mockito.verify(silent, times(1)).execute(sendMessage);
    }

    @Test
    void whenQuotattionComandIsInvoked_thenRespondWithInformativeMessage() {
        //Save crypto quotation
        Crypto newCrypto = new Crypto(CryptoEnum.WLDUSDT.toString(), 1200.00);
        cryptoService.saveCrypto(newCrypto);

        //Incoming update
        Update update = builder.anyUpdateWithText("Cotizacion actual");

        cryptoBot.onUpdateReceived(update);

        Crypto crypto = cryptoService.getCryptoBySymbol(CryptoEnum.WLDUSDT.toString());
        SendMessage sendMessage = builder.expectedMessageWithText("Cotización de Worldcoin: \n" + crypto.getPrice() + " USDT \n" + String.format("%.2f", crypto.getArsPrice()) + " ARS");

        Mockito.verify(silent, times(1)).execute(sendMessage);
    }


    @Test
    void whenCancelCommandIsInvoked_thenRespondWithConfirmationMessage() {
        //Incoming update
        Update update = builder.anyUpdateWithText("Cancelar mensajes");

        cryptoBot.onUpdateReceived(update);

        SendMessage sendMessage = builder.expectedMessageWithText("¡Listo! \nYa no recibiras mensajes");

        Mockito.verify(silent, times(1)).execute(sendMessage);
    }


    @Test
    void whenAnUnexpectedMessageArrives_thenRespondErrorMessage() {
        //Incoming update
        Update update = builder.anyUpdateWithText("sdfefwefwef");

        cryptoBot.onUpdateReceived(update);

        SendMessage sendMessage = builder.expectedMessageWithText("¡Ups! Disculpa, no entendí");

        Mockito.verify(silent, times(1)).execute(sendMessage);
    }
}
