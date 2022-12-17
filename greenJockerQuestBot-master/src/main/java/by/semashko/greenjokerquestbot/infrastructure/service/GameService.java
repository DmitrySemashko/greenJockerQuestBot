package by.semashko.greenjokerquestbot.infrastructure.service;

import by.semashko.greenjokerquestbot.domain.persistence.entity.Game;

public interface GameService {

    Game create(String domain,String gameId);

}
