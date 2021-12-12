package com.example.hw2.activities.objects;

public class Player {

    private String PlayerName;
 //   private String currentDateTimeString;
    private int score = 0;
    private double lat = 0.0;
    private double lon = 0.0;

    public Player() { }


    public String getPlayerName() {
        return PlayerName;
    }

    public Player setPlayerName(String playerName) {
        PlayerName = playerName;
        return this;
    }

//    public String getTime() {
//        return currentDateTimeString;
//    }
//
//    public Player setTime(String time) {
//        this.currentDateTimeString = time;
//        return this;
//    }

    public int getScore() {
        return score;
    }

    public Player setScore(int score) {
        this.score = score;
        return this;
    }

    public double getLat() {
        return lat;
    }

    public Player setLat(double lat) {
        this.lat = lat;
        return this;
    }

    public double getLon() {
        return lon;
    }

    public Player setLon(double lon) {
        this.lon = lon;
        return this;
    }




}
