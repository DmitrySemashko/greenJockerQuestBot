package by.semashko.greenjokerquestbot.bot.handler.message;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import by.semashko.greenjokerquestbot.bot.keyboard.InlineKeyboardMarkupBuilder;
import by.semashko.greenjokerquestbot.exception.InvalidUrlException;
import by.semashko.greenjokerquestbot.model.AuthorizationResponse;
import by.semashko.greenjokerquestbot.service.AuthorizationService;
import by.semashko.greenjokerquestbot.service.ReplyMessageService;
import by.semashko.greenjokerquestbot.util.UrlParser;
import lombok.*;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.IOException;

@Slf4j
@Data
@Component
public class SettingMessageHandler implements MessageHandler {

    @Autowired
    private  ReplyMessageService messageService;
    @Autowired
    private AuthorizationService authorizationService;

    private AuthorizationResponse response;

    private static String domain = null;


    public boolean canHandle(BotEvent event) {
        return event.equals(BotEvent.SETTING);
    }

    @Override
    public BotApiMethod<Message> handle(Message message) {

        if (message.getText().equals("Регистрация игры")){
            return messageService.getTextMessage(message.getChatId().toString(), "Введите ссылку на игру");
        }
        try {
            if (domain == null) {
                domain = UrlParser.getDomain(message.getText());
                log.info(domain);
                return messageService.getTextMessage(message.getChatId().toString(), "Ввдите логин и пароль через пробел");
            }

            String[] loginAndPassword = message.getText().split(" ");
            String login = loginAndPassword[0];
            String password = loginAndPassword[1];

            response = authorizationService.authorization(domain,login,password);

            if (response.getError() != 0){
                return messageService.getTextMessage(message.getChatId().toString(),response.getMessage());
            }else {

            }

        }catch (InvalidUrlException | IOException e){
            return messageService.getTextMessage(message.getChatId().toString(),e.getMessage());
        }
        return null;
    }




    private SendMessage getButtonAddToChat(Long chatId){
        return InlineKeyboardMarkupBuilder.create(chatId)
                .setText("add to chat")
                .row()
                .buttonWithURL("add to chat", "https://t.me/GreenJokerEn_bot?startgroup=XXXX")
                .endRow()
                .build();
    }
}
