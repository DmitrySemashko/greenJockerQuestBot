package by.semashko.greenjokerquestbot.bot;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class BotEventUserContext implements BotEventObserved {

    private final Map<Long, BotEvent> events = new HashMap<>();

    @Override
    public BotEvent getCurrentEventForUserById(Long id) {
        return events.getOrDefault(id, BotEvent.MENU);
    }

    @Override
    public void setCurrentEventUserWithId(Long id, BotEvent event) {
        events.put(id, event);
    }
}
