package com.utopy.cryptobot.bot;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.db.DBContext;
import org.telegram.abilitybots.api.sender.SilentSender;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import com.utopy.cryptobot.model.Chat;
import com.utopy.cryptobot.model.Crypto;
import com.utopy.cryptobot.model.CryptoEnum;
import com.utopy.cryptobot.service.CryptoService;
import com.utopy.cryptobot.service.ChatService;


@Component
public class CryptoBot extends AbilityBot {

    @Value("${bot.creatorId}")
    private String creatorId;

    @Autowired
    private ChatService chatService;

    @Autowired CryptoService cryptoService;

    @Autowired
    public CryptoBot(Environment env, DBContext dbContext) {
        super(env.getProperty("bot.token"), "WorldcoinArgbot", dbContext);
    }

    @Override
    public long creatorId() {
        return Long.parseLong(creatorId);
    }


    void setSilentSender(SilentSender silentSender) {
        this.silent = silentSender;
    }


    public void onUpdateReceived(Update newUpdate) {
        Long chatID = newUpdate.getMessage().getChatId();
        switch (newUpdate.getMessage().getText()) { 
            case "/start":
                startCommand(chatID);
                break;
            case "Cancelar mensajes":
                cancelCommand(chatID);
                break;
            case "Cotizacion actual":
                quotationCommand(chatID);
                break;
            default:
                sendMessageTo(chatID, "¡Ups! Disculpa, no entendí");
          }
    }


    private void startCommand(Long chatId){
        ReplyKeyboardMarkup replyKeyboardMarkup = generateReplyKeyboardMarkupWithTwoButtons(
                                                        "Cancelar mensajes",
                                                        "Cotizacion actual");
        Chat newChat = new Chat(chatId);
        this.chatService.saveChat(newChat);
        sendWithKeyboardMarkup(
            "¡Hola! \nTe notificaré periodicamente la cotización de Worldcoin",
            chatId,
            replyKeyboardMarkup);
    }


    private void quotationCommand(Long chatID){
        Crypto crypto = cryptoService.getCryptoBySymbol(CryptoEnum.WLDUSDT.toString());
        sendMessageTo(chatID, 
                    "Cotización de Worldcoin: \n" + crypto.getPrice() + " USDT \n" + String.format("%.2f", crypto.getArsPrice()) + " ARS");
    }


    private void cancelCommand(Long chatId){
        this.chatService.deleteChat(chatId);
        sendMessageTo(chatId, "¡Listo! \nYa no recibiras mensajes");
    }


    public void execute(SendMessage message){
        silent.execute(message);
    }


    public void sendMessageTo(Long telegramId, String text){
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setChatId(telegramId);
        execute(message);
    }


    @Scheduled(cron = "${broadcast.cron.expression:NONE}")    
    public void broadcastCryptoQuotation(){
        this.chatService.getAllChats().forEach(u -> {
            quotationCommand(u.getChatID());
        });
    }


    private ReplyKeyboardMarkup generateReplyKeyboardMarkupWithTwoButtons(String text1, String text2) {
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);

        KeyboardButton keyboardButton1 = new KeyboardButton();
        keyboardButton1.setText(text1);
        KeyboardButton keyboardButton2 = new KeyboardButton();
        keyboardButton2.setText(text2);

        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(keyboardButton1);
        keyboardRow.add(keyboardButton2);

        replyKeyboardMarkup.setKeyboard(List.of(keyboardRow));
        return replyKeyboardMarkup;
    }


    private void sendWithKeyboardMarkup(String text, Long chatId, ReplyKeyboard keyboard) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText(text);
        sendMessage.setParseMode(ParseMode.MARKDOWN);
        sendMessage.setChatId(chatId);
        sendMessage.setReplyMarkup(keyboard);
        execute(sendMessage);
    }

}

