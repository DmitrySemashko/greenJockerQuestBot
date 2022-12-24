package by.semashko.greenjokerquestbot.infrastructure.service.impl;

import by.semashko.greenjokerquestbot.domain.persistence.UserRepository;
import by.semashko.greenjokerquestbot.domain.persistence.entity.Game;
import by.semashko.greenjokerquestbot.domain.persistence.entity.User;
import by.semashko.greenjokerquestbot.infrastructure.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__ (@Autowired))
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public boolean save(String telegramChatId, Game game) {
        User user = repository.findByTelegramId(telegramChatId);
        if (user != null){
            return false;
        }
        user = new User();
        user.setTelegramId(telegramChatId);
        user.setGame(game);
        repository.save(user);
        return true;
    }

    @Override
    public void delete(String chatId) {
        User user = repository.findByTelegramId(chatId);
        repository.delete(user);
    }

    @Override
    public User getByChatId(String chatId) {
        return repository.findByTelegramId(chatId);
    }
}
