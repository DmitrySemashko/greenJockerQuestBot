package by.semashko.greenjokerquestbot.infrastructure.service.impl;

import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
import by.semashko.greenjokerquestbot.domain.model.Level;
import by.semashko.greenjokerquestbot.domain.model.LevelAction;
import by.semashko.greenjokerquestbot.domain.model.Task;
import by.semashko.greenjokerquestbot.infrastructure.rest.RestAPI;
import by.semashko.greenjokerquestbot.infrastructure.service.WatchEngine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static by.semashko.greenjokerquestbot.util.StringConstants.LEVEL_ACTION_ANSWER;
import static by.semashko.greenjokerquestbot.util.StringConstants.LEVEL_ID;
import static by.semashko.greenjokerquestbot.util.StringConstants.LEVEL_NUMBER;

@Service
@AllArgsConstructor(onConstructor = @__ (@Autowired))
@Getter
@Setter
public class GameSessionService {

    private WatchEngine engine;
    private RestAPI api;

    private static Long telegramChatId;

    public String sendCode(int gameId,String url,String levelId, String levelNumber, String code){

        if (isCode(code)){
            String textAnswer = code.substring(0, code.length()-1);
            Map<String,String> params = new HashMap<>();
            params.put(LEVEL_ID,levelId);
            params.put(LEVEL_NUMBER,levelNumber);
            params.put(LEVEL_ACTION_ANSWER,textAnswer.trim());

        }
        return null;
    }

    public boolean isGameActive(){
        return engine.getGameEngineModelService().getModel().getEvent() == 0;
    }

    public GameEngineModel getModel(){
        return engine.getGameEngineModelService().getModel();
    }

    public Level getLevel(){
        return engine.getGameEngineModelService()
                .getModel()
                .getLevel();
    }

    public Task getTask(){
        return engine.getGameEngineModelService()
                .getModel()
                .getLevel()
                .getTask()
                .stream()
                .findFirst()
                .orElseThrow();
    }

    public void setTelegramChatId(Long telegramId) {
        engine.setTelegramChatId(telegramId);
    }

    private boolean isCode(String code){
        return code.startsWith("/");
    }

}
