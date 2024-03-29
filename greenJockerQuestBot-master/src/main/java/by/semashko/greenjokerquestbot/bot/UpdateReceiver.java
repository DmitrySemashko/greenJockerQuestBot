package by.semashko.greenjokerquestbot.bot;

import by.semashko.greenjokerquestbot.bot.handler.BotEventHandler;
import by.semashko.greenjokerquestbot.bot.handler.member.ChatMemberHandler;
import by.semashko.greenjokerquestbot.infrastructure.service.ReplyMessageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
@Data
@Component
@AllArgsConstructor
public class UpdateReceiver {

    private final BotEventHandler eventHandler;
    private final BotEventUserContext eventUserContext;
    private final ReplyMessageService replyMessageService;
    private final ChatMemberHandler chatMemberHandler;

    public PartialBotApiMethod<? extends Serializable> handleUpdate(Update update) throws InterruptedException, ExecutionException {
        if (update.hasMessage()) {
            Message message = update.getMessage();
            BotEvent event = getBotCondition(message);
            return eventHandler.handleTextMessageByEvent(message, event);
        } else if (update.hasMyChatMember()) {
            return chatMemberHandler.handleChatMember(update.getMyChatMember());
        }
        return replyMessageService.leaveChat(update.getMessage().getChatId().toString());
    }

    private BotEvent getBotCondition(Message message) {
        Long userId = message.getFrom().getId();
        String userTextMessage = message.getText();
        List<User> newChatMembers = message.getNewChatMembers();
        if (message.hasText()) {
            return getBotTextCondition(userId, userTextMessage, message.getChat().isGroupChat());
        }
        return getBotNewChatMemberCondition(userId, newChatMembers);
    }

    private BotEvent getBotTextCondition(Long userId, String userTextMessage, boolean isGroupChat) {
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
            case "/start@GreenJokerEn_bot 1111":
                botEvent = isGroupChat ? BotEvent.START_GAME_SESSION : BotEvent.MENU;
                break;
            case "next level":
                botEvent = BotEvent.START_GAME_SESSION;
                break;
            default:
                botEvent = eventUserContext.getCurrentEventForUserById(userId);
        }
        eventUserContext.setCurrentEventUserWithId(userId, botEvent);
        return botEvent;
    }

    private BotEvent getBotNewChatMemberCondition(Long id, List<User> newChatMembers) {
        return newChatMembers.isEmpty() ? null : BotEvent.NEW_CHAT_MEMBER;
    }
}
