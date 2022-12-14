package by.semashko.greenjokerquestbot.infrastructure.rest;

import by.semashko.greenjokerquestbot.domain.model.AuthorizationResponse;
import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;
import java.util.Map;

@Component
@Slf4j
public class RestAPI {

    public AuthorizationResponse authorize(String url, Map<String,String> loginAndPassword) throws IOException {

        ApiEngineInterface apiEngineInterface = RestClient.getApiEngine(url);
        Call<AuthorizationResponse> responseCall = apiEngineInterface.authorization(loginAndPassword);
        Response<AuthorizationResponse> response = responseCall.execute();
        return response.body();
    }

    public GameEngineModel checkStateGame(String url, int gameId) throws IOException {

        ApiEngineInterface apiEngineInterface = RestClient.getApiEngine(url);
        Call<GameEngineModel> gameEngineModelCall = apiEngineInterface.getStateGame(gameId);
        Response<GameEngineModel> response = gameEngineModelCall.execute();
        return response.body();
    }
}
