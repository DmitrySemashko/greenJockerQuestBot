package by.semashko.greenjokerquestbot.infrastructure.rest;


import by.semashko.greenjokerquestbot.domain.model.AuthorizationResponse;
import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
import by.semashko.greenjokerquestbot.domain.model.LevelAction;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.Map;

public interface ApiEngineInterface {

    @GET("GameEngines/Encounter/Play/{GameId}?json=1")
    Call<GameEngineModel> getStateGame(@Path("GameId") int gameId);
    @FormUrlEncoded
    @POST("login/signin?json=1")
    Call<AuthorizationResponse> authorization(@FieldMap(encoded = true) Map<String,String> fields);
    @POST("GameEngines/Encounter/Play/{GameId}?json=1")
    Call<LevelAction> sendAnswer(@Path("GameId") int id, @FieldMap Map<String,String> fields);
}
