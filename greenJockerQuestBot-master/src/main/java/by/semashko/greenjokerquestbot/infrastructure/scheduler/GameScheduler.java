package by.semashko.greenjokerquestbot.infrastructure.scheduler;

import by.semashko.greenjokerquestbot.infrastructure.service.WatchEngine;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.PeriodicTrigger;
import org.springframework.stereotype.Service;

@Service
@Data
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class GameScheduler {

    private ThreadPoolTaskScheduler threadPoolTaskScheduler;


    public void startWatchEngine(SchedulerTask task, WatchEngine engine, PeriodicTrigger trigger) {
        engine.setTask(task);
        scheduleRequestServer(engine, trigger);



    }

    private void scheduleRequestServer(WatchEngine engine, PeriodicTrigger trigger) {
        threadPoolTaskScheduler.schedule(engine, trigger);
    }

}
