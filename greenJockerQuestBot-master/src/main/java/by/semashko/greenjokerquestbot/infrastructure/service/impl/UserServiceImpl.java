package by.semashko.greenjokerquestbot.infrastructure.service.impl;

import by.semashko.greenjokerquestbot.domain.persistence.UserRepository;
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
    public void save(User user) {
        repository.save(user);
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
