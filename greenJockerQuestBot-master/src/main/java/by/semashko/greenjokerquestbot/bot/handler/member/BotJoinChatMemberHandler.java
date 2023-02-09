package by.semashko.greenjokerquestbot.bot.handler.member;

import by.semashko.greenjokerquestbot.infrastructure.service.ReplyMessageService;
import by.semashko.greenjokerquestbot.infrastructure.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated;

import java.io.Serializable;

@Component
@Data
@AllArgsConstructor(onConstructor = @__ (@Autowired))
public class BotJoinChatMemberHandler implements ChatMemberHandler {

    private UserService userService;
    private ReplyMessageService messageService;

    @Override
    public PartialBotApiMethod<? extends Serializable> handleChatMember(ChatMemberUpdated member) {
        String memberId= member.getFrom().getId().toString();
        String userTelegramId = userService.getByChatId(memberId).getTelegramId();
        if (memberId.equals(userTelegramId)) {
            return messageService.getTextMessage(member.getChat().getId().toString(),"Всем привет, а вот и я");
        }
        return messageService.leaveChat(member.getChat().getId().toString());
    }

}
