package com.example.hw2.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.activities.R;
import com.example.hw2.activities.objects.MyDB;
import com.example.hw2.activities.objects.Player;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class Fragment_Scores<listView> extends Fragment {

    private MyDB myDB;
    private CallBack_List callBack_list;

    private ArrayList<Player> scores;
    private MaterialButton[][] listTable;
    private AppCompatActivity activity;


    public void setActivity(AppCompatActivity activity) {
        this.activity = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scores, container, false);

        findViews(view);
        addPlayer();
        initViewOnMap();
        return view;
    }

    private void findViews(View view) {
        listTable = new MaterialButton[][]{

                { view.findViewById(R.id.tabelName1), view.findViewById(R.id.tabelScore1)},
                { view.findViewById(R.id.tabelName2), view.findViewById(R.id.tabelScore2)},
                { view.findViewById(R.id.tabelName3), view.findViewById(R.id.tabelScore3)},
                { view.findViewById(R.id.tabelName4), view.findViewById(R.id.tabelScore4)},
                { view.findViewById(R.id.tabelName5), view.findViewById(R.id.tabelScore5)},
                { view.findViewById(R.id.tabelName6), view.findViewById(R.id.tabelScore6)},
                { view.findViewById(R.id.tabelName7), view.findViewById(R.id.tabelScore7)},
                { view.findViewById(R.id.tabelName8), view.findViewById(R.id.tabelScore8)},
                { view.findViewById(R.id.tabelName9), view.findViewById(R.id.tabelScore9)},
                { view.findViewById(R.id.tabelName10), view.findViewById(R.id.tabelScore10)}
        };
    }

    private void addPlayer() {
        scores = myDB.getMyScores();
        if(scores.size()<10){
            for (int i = 0; i < scores.size(); i++) {
                listTable[i][0].setText(scores.get(i).getPlayerName());
                listTable[i][1].setText(String.valueOf(scores.get(i).getScore()));
            }
        }else{
            for (int i = 0; i < 10; i++) {
                listTable[i][0].setText(scores.get(i).getPlayerName());
                listTable[i][1].setText(String.valueOf(scores.get(i).getScore()));
            }
        }

    }

    public void setCallBack_list(CallBack_List callBackList) {
        callBack_list = callBackList;
    }

    public void setMyDB(MyDB myDB) {
        this.myDB = myDB;
    }


    private void initViewOnMap(){
        ArrayList <Player> players = myDB.getMyScores();
        int tempSize;
        if(players.size()<10){
            tempSize=players.size();
        }else{
            tempSize=10;
        }
        for (int i = 0; i < tempSize; i++) {
            Player tempPlayer = players.get(i);
            listTable[i][0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack_list.ZoomOnMap(tempPlayer.getLat(),tempPlayer.getLon());
                }
            });
            listTable[i][1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack_list.ZoomOnMap(tempPlayer.getLat(),tempPlayer.getLon());
            }
        });
    }
}


}