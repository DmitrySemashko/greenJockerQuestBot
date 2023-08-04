package by.semashko.greenjokerquestbot.infrastructure.service.impl;

import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
import by.semashko.greenjokerquestbot.domain.model.Level;
import by.semashko.greenjokerquestbot.domain.model.Task;
import by.semashko.greenjokerquestbot.infrastructure.service.WatchEngine;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__ (@Autowired))
@Getter
@Setter
public class GameSessionService {

    private WatchEngine engine;

    private static Long telegramChatId;

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

}
