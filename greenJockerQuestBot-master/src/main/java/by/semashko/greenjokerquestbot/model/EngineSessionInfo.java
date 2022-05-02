package by.semashko.greenjokerquestbot.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EngineSessionInfo {
    @Getter
    @Setter
    private long lastEventTime = 0;

    @Getter
    @Setter
    private Map<String,String> cookies = null;
    @Setter
    @Getter
    private String PLAY_URL;

    @Setter
    @Getter
    private String ENGINE_URL;

    public EngineSessionInfo(long lastEventTime, Map<String, String> cookies, String PLAY_URL, String ENGINE_URL) {
        this.lastEventTime = lastEventTime;
        this.cookies = cookies;
        this.PLAY_URL = PLAY_URL;
        this.ENGINE_URL = ENGINE_URL;
    }

    public EngineSessionInfo() {

    }


}
