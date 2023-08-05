package by.semashko.greenjokerquestbot.domain.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Data
public class Level {

    @SerializedName("LevelId")
    private int levelId;
    @SerializedName("Name")
    private String name;
    @SerializedName("Number")
    private String number;
    @SerializedName("Timeout")
    private int timeout;
    @SerializedName("TimeoutAward")
    private int timeoutAward;
    @SerializedName("TimeoutSecondsRemain")
    private int timeoutSecondsRemain;
    @SerializedName("IsPassed")
    private boolean isPassed;
    @SerializedName("Dismissed")
    private boolean dismissed;
    @SerializedName("HasAnswerBlockRule")
    private boolean hasAnswerBlockRule;
    @SerializedName("BlockDuration ")
    private int blockDuration;
    @SerializedName("BlockTargetId")
    private int blockTargetId;
    @SerializedName("AttemtsPeriod")
    private int attemtsPeriod;
    @SerializedName("RequiredSectorsCount")
    private int requiredSectorsCount;
    @SerializedName("PassedSectorsCount")
    private int passedSectorsCount;
    @SerializedName("SectorsLeftToClose ")
    private int sectorsLeftToClose;
    @SerializedName("MixedActions")
    private List<MixedActions> mixedActions;
    @SerializedName("Messages")
    private List<Message> messages;
    @SerializedName("Tasks")
    private List<Task> task;
    @SerializedName("Helps")
    private List<Help> helps;
    @SerializedName("PenaltyHelps")
    private List<PenaltyHelp> penaltyHelps;
    @SerializedName("Sectors")
    private List<Sector> sectors;
    @SerializedName("Bonuses")
    private List<Bonus> bonuses;

}
