package by.semashko.greenjokerquestbot.infrastructure.service;

import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
import by.semashko.greenjokerquestbot.domain.persistence.entity.Game;
import by.semashko.greenjokerquestbot.infrastructure.rest.RestAPI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.Callable;

@Service
@Data
@Getter
@Setter

public class WatchEngine implements Callable<GameEngineModel> {
    @Autowired
    private final RestAPI api;
    @Autowired
    private UserService service;

    private Long telegramChatId;


    @Override
    public GameEngineModel call() throws IOException {
        Game game = service.getByChatId(telegramChatId.toString()).getGame();
        return api.checkStateGame(game.getDomain(),Integer.parseInt(game.getGameId()));
    }
}
