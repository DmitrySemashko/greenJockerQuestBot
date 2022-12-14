package by.semashko.greenjokerquestbot.infrastructure.service.impl;

import by.semashko.greenjokerquestbot.domain.persistence.entity.Game;
import by.semashko.greenjokerquestbot.infrastructure.service.GameService;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    @Override
    public boolean create(Game game) {
        return false;
    }
}
