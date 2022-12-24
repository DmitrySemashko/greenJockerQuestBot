package by.semashko.greenjokerquestbot.bot.handler;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.io.Serializable;

public interface Handler<T> {

    boolean canHandle(BotEvent event);

    BotApiMethod<? extends Serializable> handle(Message message);
}
