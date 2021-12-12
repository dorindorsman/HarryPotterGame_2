package com.example.hw2.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.example.activities.R;
import com.example.hw2.activities.objects.Player;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class Activity_Top extends AppCompatActivity  {

    private Fragment_Scores fragment_Scores;
    private Fragment_Map fragmentMap;
    private static CallBack_MYDB callBack_mydb;

    private MediaPlayer theme_music;
    private MediaPlayer menu_music;
    private MaterialButton panel_BTN_Back_Menu;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        //fragment_Scores
        fragment_Scores = new Fragment_Scores();
        fragment_Scores.setActivity(this);
        fragment_Scores.setMyDB(callBack_mydb.getMyDB());
        fragment_Scores.setCallBack_list(callBack_list);
        getSupportFragmentManager().beginTransaction().add(R.id.frameScores, fragment_Scores).commit();

        //fragment_Map
        fragmentMap= new Fragment_Map();
        fragmentMap.setActivity(this);
        getSupportFragmentManager().beginTransaction().add(R.id.frameMap, fragmentMap).commit();

        panel_BTN_Back_Menu=findViewById(R.id.panel_BTN_Back_Menu);
        initMusic();
        initBtnClick();

    }

    private void initMusic() {
        theme_music = MediaPlayer.create(Activity_Top.this,R.raw.sound_theme_song);
        menu_music = MediaPlayer.create(Activity_Top.this,R.raw.sound_menu);
    }


    public static void setCallBack_myDB(CallBack_MYDB callBack_myDB) {
        callBack_mydb = callBack_myDB;
    }

    CallBack_List callBack_list = new CallBack_List(){
            @Override
            public void ZoomOnMap(double lat, double lon) {
                zoomPlayer(lat,lon);
            }
        };

    private void zoomPlayer(double lat, double lon) {
        GoogleMap gm = fragmentMap.getmMap();
        LatLng point = new LatLng(lat, lon);
        gm.addMarker(new MarkerOptions()
                .position(point)
                .title("* Crash Site * | Pilot Name: " ));
        gm.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 13.0f));
    }


    private void initBtnClick() {

        panel_BTN_Back_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackToMenu();
            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        theme_music.pause();
    }

    @Override
    protected void onStart() {
        super.onStart();
        theme_music.start();
        theme_music.setLooping(true);
    }

    private void BackToMenu() {
        theme_music.stop();
        menu_music.start();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }


}