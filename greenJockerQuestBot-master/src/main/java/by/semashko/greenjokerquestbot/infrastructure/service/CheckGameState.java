package by.semashko.greenjokerquestbot.infrastructure.service;

import by.semashko.greenjokerquestbot.domain.enums.StateGame;
import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
import by.semashko.greenjokerquestbot.infrastructure.rest.RestAPI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor(onConstructor = @__ (@Autowired))
@Getter
public class CheckGameState {

    private final RestAPI api;

    public StateGame getStateGame(String url, int id) throws IOException {
        GameEngineModel model = api.checkStateGame(url,id);
        return setStateGames(model.getEvent());

    }

    public StateGame asyncWatchGameState(String url, int id){

        return null;
    }

    public StateGame setStateGames(int event) {
        return Arrays.stream(StateGame.values())
                .filter(s -> s.getNumberError()== event)
                .findAny()
                .orElseThrow(NoSuchElementException::new);
    }


}
