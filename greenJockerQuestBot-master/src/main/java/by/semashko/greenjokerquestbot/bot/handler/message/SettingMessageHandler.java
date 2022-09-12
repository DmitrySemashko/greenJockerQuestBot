package by.semashko.greenjokerquestbot.bot.handler.message;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import by.semashko.greenjokerquestbot.bot.keyboard.InlineKeyboardMarkupBuilder;
import by.semashko.greenjokerquestbot.exception.InvalidUrlException;
import by.semashko.greenjokerquestbot.domain.model.AuthorizationResponse;
import by.semashko.greenjokerquestbot.service.AuthorizationServiceImpl;
import by.semashko.greenjokerquestbot.service.ReplyMessageService;
import by.semashko.greenjokerquestbot.util.UrlParser;
import lombok.*;
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
    private ReplyMessageService messageService;
    @Autowired
    private AuthorizationServiceImpl authorizationService;

    private static String domain = null;


    public boolean canHandle(BotEvent event) {
        return event.equals(BotEvent.SETTING);
    }

    @Override
    public BotApiMethod<Message> handle(Message message) {

        if (message.getText().equals("Регистрация игры")) {
            return messageService.getTextMessage(message.getChatId().toString(), "Введите ссылку на игру");
        }
        try {
            if (domain == null) {
                domain = UrlParser.getDomain(message.getText());
                log.info(domain);
                return messageService.getTextMessage(message.getChatId().toString(), "Ввдите логин и пароль через пробел");
            }

            AuthorizationResponse response = authorizationService.authorization(domain, message.getText());

            if (response.getError() != 0) {
                return messageService.getTextMessage(message.getChatId().toString(), response.getMessage());
            } else {
                return getButtonAddToChat(message.getChatId());
            }

        } catch (InvalidUrlException | IOException e) {
            return messageService.getTextMessage(message.getChatId().toString(), e.getMessage());
        }
    }


    private SendMessage getButtonAddToChat(Long chatId) {
        return InlineKeyboardMarkupBuilder.create(chatId)
                .setText("Добавить бота в чат")
                .row()
                .buttonWithURL("add to chat", "https://t.me/GreenJokerEn_bot?startgroup=XXXX")
                .endRow()
                .build();
    }
}
