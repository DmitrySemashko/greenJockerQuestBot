package by.semashko.greenjokerquestbot.infrastructure.scheduler;

import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Service
@Getter
public class GameScheduler {

    private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

}
