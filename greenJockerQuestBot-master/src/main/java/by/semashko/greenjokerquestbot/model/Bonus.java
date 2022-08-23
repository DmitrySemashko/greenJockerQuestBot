package by.semashko.greenjokerquestbot.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Bonus {

    @SerializedName("BonusId")
    private int bonusId;
    @SerializedName("Name")
    private String name;
    @SerializedName("Number")
    private int number;
    @SerializedName("Task")
    private String task;
    @SerializedName("Help")
    private String help;
    @SerializedName("IsAnswered")
    private boolean isAnswered;
    @SerializedName("Expired")
    private boolean expired;
    @SerializedName("SecondsToStart")
    private int secondsToStart;
    @SerializedName("SecondsLeft")
    private int secondsLeft;
    @SerializedName("AwardTime")
    private int awardTime;
    @SerializedName("Negative")
    private boolean negative;


}
