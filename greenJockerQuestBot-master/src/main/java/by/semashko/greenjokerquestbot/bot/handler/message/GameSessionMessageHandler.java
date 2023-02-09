package by.semashko.greenjokerquestbot.bot.handler.message;

import by.semashko.greenjokerquestbot.bot.BotEvent;
import by.semashko.greenjokerquestbot.bot.handler.Handler;
import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
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

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

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
    public BotApiMethod<Message> handle(Message message) throws ExecutionException, InterruptedException {
        if (message.getText().equals("/start@GreenJokerEn_bot")){
            Long telegramId = message.getFrom().getId();
            watchEngine.setTelegramChatId(telegramId);
            Future<GameEngineModel> future = scheduler.getResult(watchEngine);
            GameEngineModel model = future.get();
            log.info(model.getGameTitle());
        }
        return null;
    }

    private boolean isGameExist(Long telegramID){
        return userService.getByChatId(telegramID.toString()) != null;
    }
}
