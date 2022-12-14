package by.semashko.greenjokerquestbot.infrastructure.service.impl;

import by.semashko.greenjokerquestbot.domain.model.AuthorizationResponse;
import by.semashko.greenjokerquestbot.infrastructure.rest.RestAPI;
import by.semashko.greenjokerquestbot.infrastructure.service.AuthorizationService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@AllArgsConstructor(onConstructor = @__ (@Autowired))
public class AuthorizationServiceImpl implements AuthorizationService {

    private static final String LOGIN = "Login";
    private static final String PASSWORD = "Password";

    @Getter
    @Setter
    private RestAPI authorization;

    @Override
    public AuthorizationResponse authorization(String domain, String message) throws IOException {

        Map<String,String> map = new HashMap<>();
        String [] loginAndPassword = message.split(" ");
        map.put(LOGIN,loginAndPassword[0]);
        map.put(PASSWORD,loginAndPassword[1]);

        return authorization.authorize(domain, map);
    }
}
