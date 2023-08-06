package by.semashko.greenjokerquestbot.infrastructure.rest;


import by.semashko.greenjokerquestbot.domain.model.AuthorizationResponse;
import by.semashko.greenjokerquestbot.domain.model.GameEngineModel;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.Map;

public interface ApiEngineInterface {

    @GET("GameEngines/Encounter/Play/{GameId}?json=1")
    Call<GameEngineModel> getStateGame(@Path("GameId") int gameId);
    @FormUrlEncoded
    @POST("login/signin?json=1")
    Call<AuthorizationResponse> authorization(@FieldMap(encoded = true) Map<String,String> fields);
    @FormUrlEncoded
    @POST("GameEngines/Encounter/Play/{GameId}?json=1")
    Call<GameEngineModel> sendAnswer(@Path("GameId") int id, @FieldMap Map<String,String> fields);
}
