package by.semashko.greenjokerquestbot.domain.enums;

import lombok.Getter;
import lombok.Setter;

public enum StateGame {

    ACTIVE(0,""),
    NO_USER(4,"Игрок не залогинен на сайте"),
    GAME_ID_NOT_FOUND(2,"Игра с указанным ID не существует"),
    GAME_NOT_START(5,"Игра не началась"),
    GAME_END(6,"Игра закончилась"),
    TEAM_NOT_USER(10, "У игрока нет команды"),
    TIME_OUT(19,"Автопереход"),
    GAME_NOT_FOUND(3,"Запрошенная игра не соответствует запрошенному Engine"),
    NOT_SUBMIT_APP(7, "Не подана заявка (игроком)"),
    NOT_SUBMIT_APP_TEAM(8,"Не подана заявка (командой)"),
    LEVEL_REMOVED(16,"Уровень снят"),
    ALL_SECTORS_GUESSES(20,"Все сектора отгаданы");

    @Setter
    @Getter
    private int numberError;
    @Setter
    @Getter
    private String description;

    StateGame(int numberError, String description) {
        this.numberError = numberError;
        this.description = description;
    }

}
