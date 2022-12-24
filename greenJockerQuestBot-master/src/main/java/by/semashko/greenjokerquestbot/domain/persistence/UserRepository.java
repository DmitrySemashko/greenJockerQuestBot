package by.semashko.greenjokerquestbot.domain.persistence;

import by.semashko.greenjokerquestbot.domain.persistence.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByTelegramId(String chatId);
}
