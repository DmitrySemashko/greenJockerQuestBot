package by.semashko.greenjokerquestbot.bot.handler.message;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import by.semashko.greenjokerquestbot.bot.handler.Handler;
import by.semashko.greenjokerquestbot.infrastructure.scheduler.GameScheduler;
import by.semashko.greenjokerquestbot.infrastructure.service.GameService;
import by.semashko.greenjokerquestbot.infrastructure.service.ReplyMessageService;
import by.semashko.greenjokerquestbot.infrastructure.service.UserService;
import by.semashko.greenjokerquestbot.infrastructure.service.WatchEngine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.concurrent.TimeUnit;

@Component
@AllArgsConstructor(onConstructor = @__ (@Autowired))
@NoArgsConstructor
@Getter
@Setter
@Slf4j
public class GameSessionMessageHandler implements Handler<Message> {

    private UserService userService;
    private GameService gameService;
    private ReplyMessageService messageService;
    private WatchEngine watchEngine;
    private GameScheduler scheduler;


    @Override
    public boolean canHandle(BotEvent event) {
        return event.equals(BotEvent.START_GAME_SESSION);
    }

    @Override
    public BotApiMethod<Message> handle(Message message) {
        if (message.getText().equals("/start@GreenJokerEn_bot") || message.getText().equals("/start@GreenJokerEn_bot 1111")){
            Long telegramId = message.getFrom().getId();
            watchEngine.setTelegramChatId(telegramId);
            scheduler.getExecutorService().scheduleAtFixedRate(watchEngine,1,2, TimeUnit.SECONDS);
            return messageService.getTextMessage(telegramId.toString(),"Слежение за игрой включено");
        }
        return null;
    }

}
