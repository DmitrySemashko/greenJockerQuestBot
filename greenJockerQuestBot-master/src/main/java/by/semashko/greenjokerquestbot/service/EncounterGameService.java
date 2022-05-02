package by.semashko.greenjokerquestbot.service;

import by.semashko.greenjokerquestbot.model.EngineSessionInfo;
import org.springframework.stereotype.Service;

@Service
public class EncounterGameService implements GameService {

    private final String LOGIN_PART = "/Login.aspx??return=/";
    private final String LOGOUT_PART = "/Login.aspx?action=logout";
    private final String ENGINE_PART = "/gameengines/encounter/play/";

    private EngineSessionInfo sessionInfo;



    @Override
    public int loginGame(String login, String password) {

        return 0;
    }
}
