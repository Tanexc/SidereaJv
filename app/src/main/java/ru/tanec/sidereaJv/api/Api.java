package ru.tanec.sidereaJv.api;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Api extends Application {

    private static ConstellationsApi cApi;
    private Retrofit retrofit;

    @Override
    public void onCreate() {
        super.onCreate();

        retrofit = new Retrofit.Builder()
                .baseUrl("https://siderea.herokuapp.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        cApi = retrofit.create(ConstellationsApi.class);
    }

    public static ConstellationsApi getApi() {
        return cApi;
    }
}
