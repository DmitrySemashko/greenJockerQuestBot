package by.semashko.greenjokerquestbot.infrastructure.service.impl;

import by.semashko.greenjokerquestbot.domain.enums.StateGame;
import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
import by.semashko.greenjokerquestbot.infrastructure.rest.RestAPI;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

@Service
@Getter
@Setter
public class GameEngineModelService {

    private final RestAPI api;

    private GameEngineModel model;

    public GameEngineModelService(RestAPI api) {
        this.api = api;
    }

    public StateGame getStateGame(String url, int id) throws IOException {
        return setStateGames(requestGetModel(url, id).getEvent());
    }

    public StateGame setStateGames(int event) {
        return Arrays.stream(StateGame.values())
                .filter(s -> s.getNumberError() == event)
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }

    public GameEngineModel requestGetModel(String url,int id) throws IOException {
        return model = api.getModel(url, id);
    }

}
