package by.semashko.greenjokerquestbot.infrastructure.service;

import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
import by.semashko.greenjokerquestbot.domain.persistence.entity.Game;
import by.semashko.greenjokerquestbot.domain.persistence.entity.User;
import by.semashko.greenjokerquestbot.infrastructure.rest.RestAPI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.Callable;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
@Getter
@Setter
public class WatchEngine implements Callable<GameEngineModel> {

    private final RestAPI api;

    private User user;

    @Override
    public GameEngineModel call() throws IOException {
        Game game = user.getGame();
        return api.checkStateGame(game.getDomain(),Integer.parseInt(game.getGameId()));
    }
}
