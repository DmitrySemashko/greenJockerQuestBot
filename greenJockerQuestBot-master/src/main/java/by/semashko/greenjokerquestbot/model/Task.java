package by.semashko.greenjokerquestbot.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Task {

    @SerializedName("TaskText")
    private String text;

}
