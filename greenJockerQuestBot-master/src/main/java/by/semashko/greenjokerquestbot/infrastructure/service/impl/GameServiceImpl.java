package by.semashko.greenjokerquestbot.infrastructure.service.impl;

import by.semashko.greenjokerquestbot.domain.persistence.GameRepository;
import by.semashko.greenjokerquestbot.domain.persistence.entity.Game;
import by.semashko.greenjokerquestbot.infrastructure.service.GameService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__ (@Autowired))
public class GameServiceImpl implements GameService {

    private final GameRepository repository;

    @Override
    public Game create(String domain, String gameId) {
       Game game = new Game();
       game.setDomain(domain);
       game.setGameId(gameId);
       return repository.save(game);
    }
}
