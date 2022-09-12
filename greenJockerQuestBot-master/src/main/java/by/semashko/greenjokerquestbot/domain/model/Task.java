package by.semashko.greenjokerquestbot.domain.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Task {

    @SerializedName("TaskText")
    private String text;

}
