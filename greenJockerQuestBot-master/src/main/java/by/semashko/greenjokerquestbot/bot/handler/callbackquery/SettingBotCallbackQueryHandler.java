package by.semashko.greenjokerquestbot.bot.handler.callbackquery;

import by.semashko.greenjokerquestbot.infrastructure.service.ReplyMessageService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;

import java.io.Serializable;
@Slf4j
@Component
public class SettingBotCallbackQueryHandler implements CallbackQueryHandler {

    @Getter
    private final String URL = "https://t.me/greenjocker_bot?startgroup";

    private final ReplyMessageService messageService;

    public SettingBotCallbackQueryHandler(ReplyMessageService messageService) {
        this.messageService = messageService;
    }

    @Override
    public PartialBotApiMethod<? extends Serializable> handleCallbackQuery(CallbackQuery callbackQuery) {

        String callbackData = callbackQuery.getData();
        String callbackId = callbackQuery.getId();
        Long chatId = callbackQuery.getMessage().getChatId();

        if ("/add".equals(callbackData)) {
            return messageService.getPopUpAnswer(callbackId, URL);
        }
        return messageService.getTextMessage(String.valueOf(chatId),"fail");
    }
}
