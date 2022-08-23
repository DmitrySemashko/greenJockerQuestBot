package by.semashko.greenjokerquestbot.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class MixedActions {

    @SerializedName("ActionId")
    private int actionId;
    @SerializedName("LevelID")
    private int levelId;
    @SerializedName("LevelNumber")
    private int levelNumber;
    @SerializedName("UserId")
    private int userId;
    @SerializedName("Kind")
    private int kind;
    @SerializedName("Login")
    private String login;
    @SerializedName("Answer")
    private String answer;
    @SerializedName("LocalDateTime")
    private String localDateTime;
    @SerializedName("IsCorrect")
    private boolean isCorrect;

}
