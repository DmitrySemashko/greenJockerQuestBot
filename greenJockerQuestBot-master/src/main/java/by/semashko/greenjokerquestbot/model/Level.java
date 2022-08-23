package by.semashko.greenjokerquestbot.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class Level {

    @SerializedName("LevelId")
    private int levelId;
    @SerializedName("Name")
    private String name;
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
    @SerializedName("StartTime ")
    private LocalTime startTime;
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
    private MixedActions mixedActions;
    @SerializedName("Messages")
    private List<Message> messages;
    @SerializedName("Task")
    private Task task;
    private List<Help> helps;
    private List<PenaltyHelp> penaltyHelps;
    private List<Bonus> bonuses;

}
