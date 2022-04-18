package com.example.androidca2frontend2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface JSONPlaceHolderAPI {

    @GET("Games")
    Call<List<Games>> getGames();

    @GET("Consoles")
    Call<List<Consoles>> getConsoles();

    @GET("Games/game/{gameName}")
    Call<List<Games>> getGameByName(@Path("gameName") String gameName);

    @PUT("Games/like/{gameName}")
    Call<List<Games>> likeGame(@Path("gameName") String gameName);
}
