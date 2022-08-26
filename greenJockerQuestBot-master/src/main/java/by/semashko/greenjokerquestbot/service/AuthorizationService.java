package by.semashko.greenjokerquestbot.service;

import by.semashko.greenjokerquestbot.model.AuthorizationResponse;
import by.semashko.greenjokerquestbot.rest.ApiEngineInterface;
import by.semashko.greenjokerquestbot.rest.RestClient;
import lombok.Data;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Data
public class AuthorizationService {

    private static final String KEY_LOGIN = "Login";

    private static final String KEY_PASSWORD = "Password";

    private Map<String,String> loginAndPassword = new HashMap<>();

    private ApiEngineInterface apiEngineInterface;

    public AuthorizationResponse authorization(String url, String login, String password) throws IOException {

        loginAndPassword.put(KEY_LOGIN,login);
        loginAndPassword.put(KEY_PASSWORD,password);

        apiEngineInterface = RestClient.getApiEngine(url);
        Call<AuthorizationResponse> responseCall = apiEngineInterface.authorization(loginAndPassword);
        Response<AuthorizationResponse> response = responseCall.execute();
        return response.body();
    }


}
