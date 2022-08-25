package by.semashko.greenjokerquestbot.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class AuthorizationResponse {
    @SerializedName("Error")
    private int error;
    @SerializedName("Message")
    private String message;
}
