package by.semashko.greenjokerquestbot.domain.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class AuthorizationResponse {
    @SerializedName("Error")
    private int error;
    @SerializedName("Message")
    private String message;
}
