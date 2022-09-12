package by.semashko.greenjokerquestbot.domain.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class PenaltyHelp {
    @SerializedName("HelpId")
    private int helpId;
    @SerializedName("Number")
    private int number;
    @SerializedName("HelpText")
    private String helpTex;
    @SerializedName("IsPenalty")
    private boolean isPenalty;
    @SerializedName("Penalty")
    private int penalty;
    @SerializedName("RequestConfirm")
    private boolean requestConfirm;
    @SerializedName("PenaltyHelpState")
    private int penaltyHelpState;
    @SerializedName("RemainSeconds")
    private int remainSeconds;
}
