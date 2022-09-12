package by.semashko.greenjokerquestbot.domain.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class EngineAction {

    @SerializedName("GameId")
    private int gameId;
    @SerializedName("LevelId")
    private int levelId;
    @SerializedName("LevelAction")
    private LevelAction levelAction;
    @SerializedName("BonusAction")
    private BonusAction bonusAction;

}
