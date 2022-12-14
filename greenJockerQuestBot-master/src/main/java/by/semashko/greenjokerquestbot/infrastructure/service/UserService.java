package by.semashko.greenjokerquestbot.infrastructure.service;

import by.semashko.greenjokerquestbot.domain.persistence.entity.User;

public interface UserService {

    void save(User user);
    void delete(String chatId);
    User getByChatId(String chatId);
}
