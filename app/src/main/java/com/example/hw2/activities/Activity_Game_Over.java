
package com.example.hw2.activities;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.activities.R;
import com.example.hw2.activities.objects.Player;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Locale;

public class Activity_Game_Over extends AppCompatActivity {

    private ImageView panel_IMG_game_over_background;
    private TextView panel_LBL_title_score_player;
    private TextView panel_LBL_score_player;
    private MaterialButton panel_BTN_start_over;
    private MaterialButton panel_BTN_Back_Menu;


    private MediaPlayer theme_music;
    private MediaPlayer exit_music;
    private MediaPlayer start_over_music;
    private MediaPlayer menu_music;

    private ArrayList<Player> myPlayers;
    private String playerName;
    private int img_player;
    private int score;
    private boolean gameMode;
    private Bundle bundle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        MyScreenUtils.hideSystemUI(this);

        if (getIntent().getBundleExtra("Bundle") != null){
            this.bundle = getIntent().getBundleExtra("Bundle");
            playerName=bundle.getString("playerName");
            score=bundle.getInt("score");
            gameMode=bundle.getBoolean("gameMode");
            img_player= bundle.getInt("player");
        } else {
            this.bundle = new Bundle();
        }

        findViews();
        initBtnClick();
        initMusic();
        presentScoreName();

    }

    private void presentScoreName() {
        panel_LBL_score_player.setText(String.valueOf(score));
        panel_LBL_title_score_player.setText(playerName.toUpperCase(Locale.ROOT)+" YOUR SCORE IS ");

    }

    private void initMusic() {
        theme_music = MediaPlayer.create(Activity_Game_Over.this,R.raw.sound_theme_song);
        start_over_music = MediaPlayer.create(Activity_Game_Over.this,R.raw.sound_start_over);
        exit_music = MediaPlayer.create(Activity_Game_Over.this,R.raw.sound_exit);
        menu_music = MediaPlayer.create(Activity_Game_Over.this,R.raw.sound_menu);
    }


    public void nextActivity(View v){
        Intent game_page = new Intent(this,Activity_Panel_Game.class);
        Bundle bundle = new Bundle();
        bundle.putInt("player",img_player);
        bundle.putBoolean("gameMode",gameMode);
        game_page.putExtra("Bundle",bundle);
        startActivity(game_page);
    }


    private void initBtnClick() {

        panel_BTN_start_over.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                theme_music.stop();
                start_over_music.start();
                nextActivity(v);
            }
        });

        panel_BTN_Back_Menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BackToMenu();
            }
        });

    }

    private void BackToMenu() {
        theme_music.stop();
        menu_music.start();
        finish();
    }

    private void findViews() {
        panel_IMG_game_over_background =findViewById(R.id.panel_IMG_game_over_background);
        panel_BTN_start_over = findViewById(R.id.panel_BTN_start_over);
        panel_BTN_Back_Menu = findViewById(R.id.panel_BTN_Back_Menu);
        panel_LBL_title_score_player=findViewById(R.id.panel_LBL_title_score_player);
        panel_LBL_score_player=findViewById(R.id.panel_LBL_score_player);
    }

    @Override
    protected void onStop() {
        theme_music.pause();
        super.onStop();
    }


    protected void onStart() {
        super.onStart();
        theme_music.start();
        theme_music.setLooping(true);
    }

}