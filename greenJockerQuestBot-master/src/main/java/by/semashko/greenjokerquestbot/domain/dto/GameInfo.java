package by.semashko.greenjokerquestbot.domain.dto;

import by.semashko.greenjokerquestbot.domain.enums.LevelSequence;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GameInfo {

    private int id;
    private String gameTitle;
    private LocalDateTime startGameTime;
    private LevelSequence sequence;

 }

