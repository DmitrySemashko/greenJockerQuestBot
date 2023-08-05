package by.semashko.greenjokerquestbot.infrastructure.rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.net.CookieManager;
import java.net.CookiePolicy;

public class RestClient {

    private static  Retrofit retrofit;

    private RestClient(){

    }

    public static ApiEngineInterface getApiEngine(String url){
        return getRetrofit(url).create(ApiEngineInterface.class);
    }

    private static Retrofit getRetrofit(String url) {
        if (retrofit == null){
            Gson gson = new GsonBuilder()
                    .create();
            return retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(getClient())
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getClient(){
        OkHttpClient.Builder client = new OkHttpClient.Builder();
        CookieManager cookieHandler = new CookieManager();
        cookieHandler.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        client.cookieJar(new JavaNetCookieJar(cookieHandler));
        return client.build();

    }


}
