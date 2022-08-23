package by.semashko.greenjokerquestbot.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class LevelAction {

    @SerializedName("Answer")
    private String answer;
    @SerializedName("IsCorrectAnswer")
    private  boolean isCorrect;

}
