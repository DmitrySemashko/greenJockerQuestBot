package by.semashko.greenjokerquestbot.rest;

import by.semashko.greenjokerquestbot.domain.model.AuthorizationResponse;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;

@Component
public class Authorization {

    public AuthorizationResponse authorize(String url, Map<String,String> loginAndPassword) throws IOException {

        ApiEngineInterface apiEngineInterface = RestClient.getApiEngine(url);
        Call<AuthorizationResponse> responseCall = apiEngineInterface.authorization(loginAndPassword);
        Response<AuthorizationResponse> response = responseCall.execute();
        return response.body();

    }
}
