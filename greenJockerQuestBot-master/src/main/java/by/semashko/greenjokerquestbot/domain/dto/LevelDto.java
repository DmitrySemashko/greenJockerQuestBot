package by.semashko.greenjokerquestbot.domain.dto;

import by.semashko.greenjokerquestbot.domain.model.Bonus;
import by.semashko.greenjokerquestbot.domain.model.Help;
import by.semashko.greenjokerquestbot.domain.model.Message;
import by.semashko.greenjokerquestbot.domain.model.MixedActions;
import by.semashko.greenjokerquestbot.domain.model.PenaltyHelp;
import by.semashko.greenjokerquestbot.domain.model.Sector;
import by.semashko.greenjokerquestbot.domain.model.Task;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class LevelDto {

    private int levelId;
    private String name;
    private int timeout;
    private int timeoutAward;
    private int timeoutSecondsRemain;
    private boolean isPassed;
    private boolean dismissed;
    private LocalTime startTime;
    private boolean hasAnswerBlockRule;
    private int blockDuration;
    private int blockTargetId;
    private int attemtsPeriod;
    private int requiredSectorsCount;
    private int passedSectorsCount;
    private int sectorsLeftToClose;
    private List<MixedActions> mixedActions;
    private List<Message> messages;
    private List<Task> task;
    private List<Help> helps;
    private List<PenaltyHelp> penaltyHelps;
    private List<Sector> sectors;
    private List<Bonus> bonuses;

}
