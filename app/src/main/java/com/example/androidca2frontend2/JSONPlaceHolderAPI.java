package com.example.androidca2frontend2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolderAPI {

    @GET("Games")
    Call<List<Games>> getGames();
}
