package by.semashko.greenjokerquestbot.infrastructure.service;

import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
import by.semashko.greenjokerquestbot.infrastructure.rest.RestAPI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class WatchEngine implements Callable<GameEngineModel> {

    private final RestAPI api;

    @Getter
    @Setter
    private GameService service;

    @Override
    public GameEngineModel call() {
        return null;
    }
}
