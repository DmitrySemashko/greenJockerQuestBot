package by.semashko.greenjokerquestbot.domain.persistence;

import by.semashko.greenjokerquestbot.domain.persistence.entity.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game,Long> {


}
