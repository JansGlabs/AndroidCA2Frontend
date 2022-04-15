package com.example.androidca2frontend2;

public class GameReportModel {

    private int id;
    private String game;
    private String genre;
    private int like;

    public GameReportModel(int id, String game, String genre, int like) {
        this.id = id;
        this.game = game;
        this.genre = genre;
        this.like = like;
    }

    @Override
    public String toString() {
        return "GameReportModel{" +
                "id=" + id +
                ", game='" + game + '\'' +
                ", genre='" + genre + '\'' +
                ", like=" + like +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
