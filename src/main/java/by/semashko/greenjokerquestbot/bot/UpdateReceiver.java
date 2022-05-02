package by.semashko.greenjokerquestbot.bot;

import by.semashko.greenjokerquestbot.bot.handler.BotEventHandler;
import by.semashko.greenjokerquestbot.bot.handler.callbackquery.CallbackQueryHandler;
import by.semashko.greenjokerquestbot.service.ReplyMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

@Slf4j
@Component
public class UpdateReceiver {

    private final BotEventHandler eventHandler;

    private final BotEventUserContext eventUserContext;

    private final ReplyMessageService replyMessageService;

    private final CallbackQueryHandler callbackQueryHandler;


    public UpdateReceiver(BotEventHandler eventHandler, BotEventUserContext eventUserContext, ReplyMessageService replyMessageService, CallbackQueryHandler callbackQueryHandler) {
        this.eventHandler = eventHandler;
        this.eventUserContext = eventUserContext;
        this.replyMessageService = replyMessageService;
        this.callbackQueryHandler = callbackQueryHandler;
    }

    public PartialBotApiMethod<? extends Serializable> handleUpdate(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            BotEvent event = getBotCondition(message);
            return eventHandler.handleTextMessageByEvent(message, event);
        }else if(update.hasCallbackQuery()){
            CallbackQuery callbackQuery = update.getCallbackQuery();

            return callbackQueryHandler.handleCallbackQuery(callbackQuery);
        }
        return replyMessageService.getTextMessage(update.getMessage().getChatId().toString(), "ebok");
    }

    private BotEvent getBotCondition(Message message) {
        Long userId = message.getFrom().getId();
        String userTextMessage = message.getText();
        BotEvent botEvent;

        switch (userTextMessage) {
            case "/start":
                botEvent = BotEvent.MENU;
                break;
            case "/help":
                botEvent = BotEvent.HELP;
                break;
            case "en.cx" :
                botEvent = BotEvent.SETTING;
                break;
            default:
                botEvent = eventUserContext.getCurrentEventForUserById(userId);
        }
        eventUserContext.setCurrentEventUserWithId(userId, botEvent);
        return botEvent;
    }
}
