package by.semashko.greenjokerquestbot.service;

import by.semashko.greenjokerquestbot.domain.model.AuthorizationResponse;
import by.semashko.greenjokerquestbot.rest.Authorization;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Data
public class AuthorizationServiceImpl implements AuthorizationService {

    private static final String LOGIN = "Login";
    private static final String PASSWORD = "Password";

    private Authorization authorization;

    @Override
    public AuthorizationResponse authorization(String domain, String message) throws IOException {

        Map<String,String> map = new HashMap<>();
        String [] loginAndPassword = message.split(" ");
        map.put(LOGIN,loginAndPassword[0]);
        map.put(PASSWORD,loginAndPassword[1]);

        return authorization.authorize(domain, map);
    }
}
