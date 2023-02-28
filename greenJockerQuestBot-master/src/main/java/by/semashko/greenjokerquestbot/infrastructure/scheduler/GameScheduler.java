package by.semashko.greenjokerquestbot.infrastructure.scheduler;

import by.semashko.greenjokerquestbot.infrastructure.service.WatchEngine;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GameScheduler {

    private ThreadPoolTaskScheduler threadPoolTaskScheduler;
    private PeriodicTrigger trigger;
    private WatchEngine watchEngine;

    @PostConstruct
    public void scheduleRequestServer(){
        threadPoolTaskScheduler.schedule(watchEngine,trigger);

    }
}
