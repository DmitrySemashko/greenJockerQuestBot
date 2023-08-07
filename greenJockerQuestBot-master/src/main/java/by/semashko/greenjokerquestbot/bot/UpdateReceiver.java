package by.semashko.greenjokerquestbot.bot;

import by.semashko.greenjokerquestbot.bot.handler.BotEventHandler;
import by.semashko.greenjokerquestbot.bot.handler.member.ChatMemberHandler;
import by.semashko.greenjokerquestbot.infrastructure.service.ReplyMessageService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UpdateReceiver {

    private BotEventHandler eventHandler;
    private BotEventUserContext eventUserContext;
    private ReplyMessageService replyMessageService;
    private ChatMemberHandler chatMemberHandler;


    public UpdateReceiver(BotEventHandler eventHandler, BotEventUserContext eventUserContext, ReplyMessageService replyMessageService, ChatMemberHandler chatMemberHandler) {
        this.eventHandler = eventHandler;
        this.eventUserContext = eventUserContext;
        this.replyMessageService = replyMessageService;
        this.chatMemberHandler = chatMemberHandler;
    }

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
            default:
                botEvent = eventUserContext.getCurrentEventForUserById(userId);
        }
        eventUserContext.setCurrentEventUserWithId(userId, botEvent);
        return botEvent;
    }

    private BotEvent getBotNewChatMemberCondition(Long id, List<User> newChatMembers) {
        return newChatMembers.isEmpty() ? null : BotEvent.NEW_CHAT_MEMBER;
    }
   @Autowired
    public void setEventHandler(BotEventHandler eventHandler) {
        this.eventHandler = eventHandler;
    }
    @Autowired
    public void setEventUserContext(BotEventUserContext eventUserContext) {
        this.eventUserContext = eventUserContext;
    }
    @Autowired
    public void setReplyMessageService(ReplyMessageService replyMessageService) {
        this.replyMessageService = replyMessageService;
    }
    @Autowired
    public void setChatMemberHandler(ChatMemberHandler chatMemberHandler) {
        this.chatMemberHandler = chatMemberHandler;
    }
}
