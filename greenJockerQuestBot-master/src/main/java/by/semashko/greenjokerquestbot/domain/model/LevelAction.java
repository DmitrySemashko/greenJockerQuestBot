package by.semashko.greenjokerquestbot.domain.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class LevelAction {

    @SerializedName("Answer")
    private String answer;
    @SerializedName("IsCorrectAnswer")
    private  boolean isCorrect;

}
