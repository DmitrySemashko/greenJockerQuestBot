package by.semashko.greenjokerquestbot.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Sector {
    @SerializedName("SectorId")
    private  int sectorId;
    @SerializedName("Order")
    private int order;
    @SerializedName("Name")
    private String name;
    @SerializedName("Answer")
    private String answer;
    @SerializedName("IsAnswered")
    private boolean isAnswered;

}
