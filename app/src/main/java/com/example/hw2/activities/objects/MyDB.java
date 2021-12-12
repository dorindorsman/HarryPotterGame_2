package com.example.hw2.activities.objects;

import java.util.ArrayList;

public class MyDB {

    private ArrayList<Player> myScores = new ArrayList<>();



    public MyDB() {}

        public ArrayList<Player> getMyScores () {
        if(myScores==null){
            myScores=new ArrayList<Player> ();
        }
            return myScores;
        }

        public MyDB setMyScores (ArrayList <Player> myScores) {
            this.myScores = myScores;
            return this;
        }




}
