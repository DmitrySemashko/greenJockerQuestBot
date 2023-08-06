package by.semashko.greenjokerquestbot.infrastructure.service;

import by.semashko.greenjokerquestbot.domain.enums.StateGame;
import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
import by.semashko.greenjokerquestbot.domain.persistence.entity.Game;
import by.semashko.greenjokerquestbot.infrastructure.rest.RestAPI;
import by.semashko.greenjokerquestbot.infrastructure.service.impl.GameEngineModelService;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
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
    private static Long telegramChatId;


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
        }
    }

    public void setTelegramChatId(Long telegramId) {
        telegramChatId = telegramId;
    }

    public void setApi(RestAPI api) {
        this.api = api;
    }

    public UserService getService() {
        return service;
    }

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }

    public GameEngineModelService getGameEngineModelService() {
        return gameEngineModelService;
    }

    @Autowired
    public void setGameEngineModelService(GameEngineModelService gameEngineModelService) {
        this.gameEngineModelService = gameEngineModelService;
    }


    public static Logger getLog() {
        return log;
    }
}
