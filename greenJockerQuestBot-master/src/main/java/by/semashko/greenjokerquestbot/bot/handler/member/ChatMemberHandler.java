package by.semashko.greenjokerquestbot.bot.handler.member;

import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.ChatMemberUpdated;

import java.io.Serializable;

public interface ChatMemberHandler {

    PartialBotApiMethod<? extends Serializable> handleChatMember(ChatMemberUpdated member);

}
