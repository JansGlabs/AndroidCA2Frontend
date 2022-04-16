package com.example.androidca2frontend2;

import com.google.gson.annotations.SerializedName;

public class Games {

    private int id;
    private String game;
    private String genre;
    private int like;

    public int getId() {
        return id;
    }

    public String getGame() {
        return game;
    }

    public String getGenre() {
        return genre;
    }

    public int getLike() {
        return like;
    }
}
