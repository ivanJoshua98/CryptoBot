package com.utopy.cryptobot.bot;

import java.util.List;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Chat;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

public class ElementBuilderBotTest {
    
    public static final Long USER_ID = 1L;

    public static final Long CHAT_ID = 1L;

    private User telegramUser = new User(USER_ID, "Pepe", false);

    public ElementBuilderBotTest(){
        super();
    }

    public Update anyUpdateWithText(String text){
        Chat chat = new Chat();
        chat.setId(CHAT_ID);

        Message message = new Message();
        message.setText(text);
        message.setChat(chat);
        message.setFrom(telegramUser);

        Update update = new Update();
        update.setMessage(message);
        return update;
    }

    
    public ReplyKeyboardMarkup twoButtonsWithText(String text1, String text2){
        KeyboardButton button1 = new KeyboardButton();
        KeyboardButton button2 = new KeyboardButton();
        button1.setText(text1);
        button2.setText(text2);
        KeyboardRow keyboardRow = new KeyboardRow();
        keyboardRow.add(button1);
        keyboardRow.add(button2);        
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        replyKeyboardMarkup.setKeyboard(List.of(keyboardRow));
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(true);
        replyKeyboardMarkup.setSelective(true);
        return replyKeyboardMarkup;
    }


    public SendMessage expectedMessageWithText(String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(CHAT_ID);
        sendMessage.setText(text);
        return sendMessage;
    }

}
