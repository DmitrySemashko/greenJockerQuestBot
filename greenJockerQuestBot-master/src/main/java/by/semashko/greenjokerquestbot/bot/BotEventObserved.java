package by.semashko.greenjokerquestbot.bot;

public interface BotEventObserved {

    BotEvent getCurrentEventForUserById(Long id);
    void setCurrentEventUserWithId(Long id, BotEvent event);
}
