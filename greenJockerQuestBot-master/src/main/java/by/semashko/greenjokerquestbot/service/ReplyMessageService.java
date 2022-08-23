package by.semashko.greenjokerquestbot.service;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.AnswerCallbackQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Service
public class ReplyMessageService {

    public SendMessage getTextMessage(String chatId, String text){
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(false);
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);

        return sendMessage;
    }

    public AnswerCallbackQuery getPopUpAnswer(String callbackId, String text){
        AnswerCallbackQuery callbackQuery = new AnswerCallbackQuery();
        callbackQuery.setCallbackQueryId(callbackId);
        callbackQuery.setUrl(text);
        callbackQuery.setShowAlert(false);
        return callbackQuery;
    }
}
