package by.semashko.greenjokerquestbot.bot.handler.message;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface MessageHandler {

    boolean canHandle(BotEvent event);

    BotApiMethod<Message> handle(Message message);
}
