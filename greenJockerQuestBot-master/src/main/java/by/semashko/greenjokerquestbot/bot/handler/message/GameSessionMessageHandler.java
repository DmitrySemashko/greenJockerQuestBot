package by.semashko.greenjokerquestbot.bot.handler.message;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

@Component
public class GameSessionMessageHandler implements MessageHandler{



    @Override
    public boolean canHandle(BotEvent event) {
        return false;
    }

    @Override
    public BotApiMethod<Message> handle(Message message) {
        return null;
    }
}
