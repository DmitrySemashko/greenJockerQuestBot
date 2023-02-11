package by.semashko.greenjokerquestbot.infrastructure.service;

import by.semashko.greenjokerquestbot.domain.enums.StateGame;
import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
import by.semashko.greenjokerquestbot.domain.persistence.entity.Game;
import by.semashko.greenjokerquestbot.infrastructure.rest.RestAPI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Data
@Getter
@Setter
@Slf4j
@AllArgsConstructor(onConstructor = @__ (@Autowired))
public class WatchEngine implements Runnable {

    private final RestAPI api;
    private UserService service;
    private GameEngineModelService gameEngineModelService;
    private static Long telegramChatId;

    @Override
    @SneakyThrows
    public void run() {
        Game game = service.getByChatId(telegramChatId.toString()).getGame();
        StateGame stateGame = gameEngineModelService.getStateGame(game.getDomain(),game.getId());
        gameEngineModelService.setStateGames(stateGame.getNumberError());
        GameEngineModel model = gameEngineModelService.requestGetModel(game.getDomain(),game.getId());
        gameEngineModelService.setModel(model);
        System.out.println(model.getEvent());
    }

    public void setTelegramChatId(Long telegramId) {
        telegramChatId = telegramId;
    }
}
