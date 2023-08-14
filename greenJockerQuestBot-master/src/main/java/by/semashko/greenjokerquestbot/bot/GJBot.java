package by.semashko.greenjokerquestbot.bot;

import by.semashko.greenjokerquestbot.domain.enums.StateGame;
import by.semashko.greenjokerquestbot.infrastructure.scheduler.GameScheduler;
import by.semashko.greenjokerquestbot.infrastructure.service.UserService;
import by.semashko.greenjokerquestbot.infrastructure.service.WatchEngine;
import by.semashko.greenjokerquestbot.infrastructure.service.impl.GameEngineModelService;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.PartialBotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

@EqualsAndHashCode(callSuper = true)
@Slf4j
@Component
@Setter
@Getter
public class GJBot extends TelegramLongPollingBot {

    @Value("${bot.username}")
    private String botUsername;
    @Value("${bot.token}")
    private String botToken;

    private UpdateReceiver updateReceiver;
    private WatchEngine engine;
    private GameScheduler scheduler;
    private PeriodicTrigger trigger;
    private UserService service;

    private static long chatId;

    @Autowired
    public GJBot(UpdateReceiver updateReceiver, WatchEngine engine, GameScheduler scheduler, UserService service, PeriodicTrigger trigger) {
        this.updateReceiver = updateReceiver;
        this.engine = engine;
        this.scheduler = scheduler;
        this.service = service;
        this.trigger = trigger;
        try {
            onUpdateReceivedEngine();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {

        PartialBotApiMethod<? extends Serializable> responseToUser = updateReceiver.handleUpdate(update);
        chatId = update.getMessage().getChatId();
        if (responseToUser instanceof BotApiMethod) {
            try {
                execute((BotApiMethod<? extends Serializable>) responseToUser);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }

    }

    private void startWatchEngine() {
        scheduler.scheduleRequestServer(engine, trigger);
    }


    private synchronized void onUpdateReceivedEngine() throws ExecutionException, InterruptedException {
        startWatchEngine();
        GameEngineModelService service = engine.getGameEngineModelService();
        if (service.getModel() != null) {
            int event = service.getModel().getEvent();
            StateGame stateGame = service.setStateGames(event);
            if (stateGame == StateGame.ACTIVE) {
                if (service.getModel().getLevel().isPassed()){
                    Update update = new Update();
                    update.setMessage(new Message());
                    update.getMessage().setText("next level");
                    PartialBotApiMethod<? extends Serializable> responseToUser = updateReceiver.handleUpdate(update);
                    if (responseToUser instanceof BotApiMethod) {
                        try {
                            execute((BotApiMethod<? extends Serializable>) responseToUser);
                        } catch (TelegramApiException e) {
                            e.printStackTrace();
                        }
                    }

                }
            }
        }else {
            wait(35);
        }
    }

}
