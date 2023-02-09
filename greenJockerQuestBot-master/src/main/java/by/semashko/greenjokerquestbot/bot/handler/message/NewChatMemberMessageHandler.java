package by.semashko.greenjokerquestbot.bot.handler.message;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import by.semashko.greenjokerquestbot.bot.handler.Handler;
import by.semashko.greenjokerquestbot.infrastructure.service.ReplyMessageService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.User;

import java.io.Serializable;
import java.util.List;

@Component
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class NewChatMemberMessageHandler implements Handler<Message> {

    private ReplyMessageService replyMessageService;

    @Override
    public boolean canHandle(BotEvent event) {
        return event.equals(BotEvent.NEW_CHAT_MEMBER);
    }

    @Override
    public BotApiMethod<? extends Serializable> handle(Message message) {
        List<User> users = message.getNewChatMembers();
        User user = users.get(0);
        if (user.getUserName().equals("GreenJokerEn_bot")) {
            return replyMessageService.getTextMessage(message.getChatId().toString(),"Игра успешно зарегистрирована");
        }
        return null;
    }


}
