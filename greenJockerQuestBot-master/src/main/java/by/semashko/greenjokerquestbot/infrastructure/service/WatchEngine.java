package by.semashko.greenjokerquestbot.infrastructure.service;

import by.semashko.greenjokerquestbot.domain.enums.StateGame;
import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
import by.semashko.greenjokerquestbot.domain.persistence.entity.Game;
import by.semashko.greenjokerquestbot.infrastructure.rest.RestAPI;
import by.semashko.greenjokerquestbot.infrastructure.scheduler.SchedulerTask;
import by.semashko.greenjokerquestbot.infrastructure.service.impl.GameEngineModelService;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Getter
@Setter
@Slf4j
public class WatchEngine implements Runnable {

    private RestAPI api;
    private UserService service;
    private GameEngineModelService gameEngineModelService;

    private SchedulerTask task;
    private static Long telegramChatId;

    @Autowired
    public WatchEngine(RestAPI api, UserService service, GameEngineModelService gameEngineModelService) {
        this.api = api;
        this.service = service;
        this.gameEngineModelService = gameEngineModelService;
    }

    @Override
    @SneakyThrows
    public void run() {
        if (telegramChatId != null) {
            String chatId = telegramChatId.toString();
            Game game = service.getByChatId(chatId).getGame();
            StateGame stateGame = gameEngineModelService.getStateGame(game.getDomain(), Integer.parseInt(game.getGameId()));
            gameEngineModelService.setStateGames(stateGame.getNumberError());
            GameEngineModel model = gameEngineModelService.requestGetModel(game.getDomain(), Integer.parseInt(game.getGameId()));
            gameEngineModelService.setModel(model);
            log.info(gameEngineModelService.getModel().getGameTitle());
            task.execute();
        }
    }

    public void setTelegramChatId(Long telegramId) {
        telegramChatId = telegramId;
    }


}
