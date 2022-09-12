package by.semashko.greenjokerquestbot.domain.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

@Data
public class Message {
    @SerializedName("OwnerId")
    private int ownerId;
    @SerializedName("OwnerName")
    private String ownerName;
    @SerializedName("MessageId")
    private int messageId;
    @SerializedName("MessageText")
    private String messageText;
}
