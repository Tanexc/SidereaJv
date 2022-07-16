package ru.tanec.sidereaJv.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ConstellationsApi {
    @GET("/api/get/cons")
    Call<List<Constellation>> getAll();

    @GET("/api/get/cons/{id}")
    Constellation getById(@Path( value="id", encoded=true ) long id);
}
