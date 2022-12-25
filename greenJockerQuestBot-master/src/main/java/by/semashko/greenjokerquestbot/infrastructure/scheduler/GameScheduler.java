package by.semashko.greenjokerquestbot.infrastructure.scheduler;

import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
import by.semashko.greenjokerquestbot.infrastructure.service.WatchEngine;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Service
@Getter
public class GameScheduler {

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public Future<GameEngineModel> getResult(WatchEngine engine){
        return executorService.schedule(engine,2, TimeUnit.SECONDS);
    }

}
