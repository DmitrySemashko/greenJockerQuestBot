package by.semashko.greenjokerquestbot.bot;

import by.semashko.greenjokerquestbot.bot.handler.BotEventHandler;
import by.semashko.greenjokerquestbot.bot.handler.callbackquery.CallbackQueryHandler;
import by.semashko.greenjokerquestbot.infrastructure.service.ReplyMessageService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.Serializable;

@Slf4j
@Data
@Component
public class UpdateReceiver {

    private BotEventHandler eventHandler;

    private BotEventUserContext eventUserContext;

    private ReplyMessageService replyMessageService;

    private CallbackQueryHandler callbackQueryHandler;


    @Autowired
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
            if (event != null) {
                return eventHandler.handleTextMessageByEvent(message, event);
            }else {
                return replyMessageService.leaveChat(update.getMessage().getChatId().toString());
            }
        } else if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();

            return callbackQueryHandler.handleCallbackQuery(callbackQuery);
        }
        return replyMessageService.leaveChat(update.getMessage().getChatId().toString());
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
            case "Регистрация игры":
                botEvent = BotEvent.SETTING;
                break;
            case "/start@GreenJokerEn_bot":
                botEvent = message.getChat().isGroupChat() ? BotEvent.START_GAME_SESSION : BotEvent.MENU;
                break;
            default:
                botEvent = eventUserContext.getCurrentEventForUserById(userId);
        }
        eventUserContext.setCurrentEventUserWithId(userId, botEvent);
        return botEvent;
    }

}
