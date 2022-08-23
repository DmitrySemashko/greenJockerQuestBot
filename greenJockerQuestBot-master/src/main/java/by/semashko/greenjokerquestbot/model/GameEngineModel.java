package by.semashko.greenjokerquestbot.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class GameEngineModel {

    @SerializedName("Event")
    private int event;
    @SerializedName("GameId")
    private int gameId;
    @SerializedName("GameNumber")
    private int gameNumber;
    @SerializedName("GameTitle")
    private String gameTitle;
    @SerializedName("LevelSequence")
    private int levelSequence;
    @SerializedName("UserId")
    private int userId;
    @SerializedName("TeamId")
    private int teamId;
    @SerializedName("EngineAction")
    private EngineAction engineAction;
    @SerializedName("Level")
    private Level level;
    @SerializedName("Levels")
    private List<Level> levels;
    @SerializedName("GameDateTimeStart")
    private LocalDateTime gameDateTimeStart;
    @SerializedName("Login")
    private String login;
    @SerializedName("TeamName")
    private String teamName;

}
