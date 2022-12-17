package by.semashko.greenjokerquestbot.infrastructure.service;

import by.semashko.greenjokerquestbot.domain.persistence.entity.Game;
import by.semashko.greenjokerquestbot.domain.persistence.entity.User;

public interface UserService {

    boolean save(String telegramChatId, Game game);
    void delete(String chatId);
    User getByChatId(String chatId);
}
