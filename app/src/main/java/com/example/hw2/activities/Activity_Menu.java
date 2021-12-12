//package com.example.activities;

package com.example.hw2.activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.activities.R;
import com.example.hw2.activities.objects.MyDB;
import com.example.hw2.activities.objects.Player;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;

public class Activity_Menu extends AppCompatActivity {

    private ImageView panel_IMG_game_menu_background;

    private LinearLayout panel_layout_name_player;
    private TextInputLayout panel_LBL_player_name;
    private MaterialButton panel_BTN_OK;

    private LinearLayout panel_layout_game_mode;
    private TextView panel_LBL_choose_game;
    private MaterialButton panel_BTN_start_Button;
    private MaterialButton panel_BTN_start_Sensor;

    private LinearLayout panel_layout_setting;
    private TextView panel_LBL_choose_player;
    private ImageButton panel_BTN_player;
    private MaterialButton panel_BTN_back;

    private LinearLayout panel_layout_buttons;
    private MaterialButton panel_BTN_setting;
    private MaterialButton panel_BTN_Top_10;
    private MaterialButton panel_BTN_exit;


    private MediaPlayer theme_music;
    private MediaPlayer exit_music;
    private MediaPlayer menu_music;

    private int index=0;
    private ArrayList<Img> imgsPlayer;

    private String playerName;
    private int playerScore =0;
    private boolean gameMode;
    private double latitude;
    private double longitude;
    private boolean scoreChanged=false;

    private boolean playerExist=false;
    private MyDB myDB = new MyDB();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        MyScreenUtils.hideSystemUI(this);
        imgsPlayer =new ArrayList<Img>();
        addImgsPlayer();

        findViews();
        initViews();
        initBtnClick();
        initMusic();

    }

    private void initMusic() {
        theme_music= MediaPlayer.create(Activity_Menu.this,R.raw.sound_theme_song);
        exit_music= MediaPlayer.create(Activity_Menu.this,R.raw.sound_exit);
        menu_music=MediaPlayer.create(Activity_Menu.this,R.raw.sound_menu);

    }

    private String getName() {

        return panel_LBL_player_name.getEditText().getText().toString();
    }


    private void addImgsPlayer() {
        imgsPlayer.add(new Img().setRes(R.drawable.img_harrypoter));
        imgsPlayer.add(new Img().setRes(R.drawable.img_hermione));
        imgsPlayer.add(new Img().setRes(R.drawable.img_malfoy));
        imgsPlayer.add(new Img().setRes(R.drawable.img_ron));
        imgsPlayer.add(new Img().setRes(R.drawable.img_hagrid));
        imgsPlayer.add(new Img().setRes(R.drawable.img_mcgonagall));
        imgsPlayer.add(new Img().setRes(R.drawable.img_snape));

    }

    private void setImgPlayer() {
        panel_layout_name_player.setVisibility(View.INVISIBLE);
        panel_layout_game_mode.setVisibility(View.INVISIBLE);
        panel_layout_buttons.setVisibility(View.INVISIBLE);
        panel_layout_setting.setVisibility(View.VISIBLE);

    }

    public void nextActivity(View v){
        Activity_Panel_Game.setCallBack_score(callBack_score);
        Intent game_page = new Intent(this,Activity_Panel_Game.class);
        Bundle bundle = new Bundle();
        bundle.putInt("player",imgsPlayer.get(index).getRes());
        bundle.putString("playerName",playerName);
        bundle.putBoolean("gameMode",gameMode);
        game_page.putExtra("Bundle",bundle);
        startActivity(game_page);

    }

    CallBack_Score callBack_score=new CallBack_Score() {
        @Override
        public void giveScore(int score) {
            playerScore=score;
        }

        @Override
        public void checkScoreChange(boolean check) {
            scoreChanged=check;
        }
    };


    private void initBtnClick() {

        panel_BTN_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_music.start();
                playerName=getName();
                for (int i = 0; i < myDB.getMyScores().size(); i++) {
                    if (myDB.getMyScores().get(i).getPlayerName().equals(playerName)) {
                        playerExist = true;
                    }
                }
                if(!playerName.equals("")){
                    panel_layout_name_player.setVisibility(View.INVISIBLE);
                    panel_layout_game_mode.setVisibility(View.VISIBLE);
                }else{
                    panel_layout_name_player.setVisibility(View.VISIBLE);
                    panel_layout_game_mode.setVisibility(View.INVISIBLE);
                }
            }
        });

        panel_BTN_start_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_music.start();
                gameMode=true;
                theme_music.stop();
                nextActivity(v);
            }
        });

        panel_BTN_start_Sensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_music.start();
                gameMode=false;
                theme_music.stop();
                nextActivity(v);
                //showPlayerNameLayout();
            }
        });

        panel_BTN_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_music.start();
                setImgPlayer();
            }
        });


        panel_BTN_player.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                index++;
                if(index==imgsPlayer.size()){
                    index=0;
                }
                panel_BTN_player.setImageResource(imgsPlayer.get(index).getRes());
            }
        });

        panel_BTN_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_music.start();
                backToMenu();
            }
        });


        panel_BTN_Top_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menu_music.start();
                showTop10();
            }
        });

        panel_BTN_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exit_music.start();
                onStop();
                finishAffinity();
            }
        });
    }

    private void showPlayerNameLayout() {
        panel_layout_buttons.setVisibility(View.VISIBLE);
        panel_layout_setting.setVisibility(View.INVISIBLE);
        panel_layout_name_player.setVisibility(View.VISIBLE);
        panel_layout_game_mode.setVisibility(View.INVISIBLE);
    }

    private void showTop10() {
        Intent game_page = new Intent(this,Activity_Top.class);
        Bundle bundle = new Bundle();
        bundle.putString("playername",playerName);
        game_page.putExtra("Bundle",bundle);
        Activity_Top.setCallBack_myDB(callBack_mydb);
        startActivity(game_page);
    }

    CallBack_MYDB callBack_mydb = new CallBack_MYDB() {
        @Override
        public MyDB getMyDB() {
            String js = MSPV3.getMe().getString("MY_DB", "");
            myDB = new Gson().fromJson(js, MyDB.class);
            return myDB;
        }

        @Override
        public void setMyDB(MyDB DB) {
                myDB =DB;
            }
        };

    private void backToMenu() {
        panel_layout_buttons.setVisibility(View.VISIBLE);
        panel_layout_setting.setVisibility(View.INVISIBLE);
        if(playerName=="" || playerName==null){
            panel_layout_name_player.setVisibility(View.VISIBLE);
            panel_layout_game_mode.setVisibility(View.INVISIBLE);
        }else{
            panel_layout_name_player.setVisibility(View.INVISIBLE);
            panel_layout_game_mode.setVisibility(View.VISIBLE);
        }
    }

    private void findViews() {
        panel_IMG_game_menu_background =findViewById(R.id.panel_IMG_game_menu_background);

        panel_layout_name_player=findViewById(R.id.panel_layout_name_player);
        panel_LBL_player_name=findViewById(R.id.panel_LBL_player_name);
        panel_BTN_OK=findViewById(R.id.panel_BTN_OK);


        panel_layout_game_mode=findViewById(R.id.panel_layout_game_mode);
        panel_BTN_start_Button = findViewById(R.id.panel_BTN_start_Button);
        panel_BTN_start_Sensor = findViewById(R.id.panel_BTN_start_Sensor);
        panel_LBL_choose_game = findViewById(R.id.panel_LBL_choose_game);

        panel_layout_setting=findViewById(R.id.panel_layout_setting);
        panel_LBL_choose_player = findViewById(R.id.panel_LBL_choose_player);
        panel_BTN_player = findViewById(R.id.panel_BTN_player);
        panel_BTN_back = findViewById(R.id.panel_BTN_back);


        panel_layout_buttons=findViewById(R.id.panel_layout_buttons);
        panel_BTN_Top_10=findViewById(R.id.panel_BTN_Top_10);
        panel_BTN_setting = findViewById(R.id.panel_BTN_setting);
        panel_BTN_exit = findViewById(R.id.panel_BTN_exit);


    }

    private void initViews() {
        getImgView(R.drawable.img_harrypoter,panel_BTN_player);
        (new LocationPlayerTrack(Activity_Menu.this)).showSettingsAlert();
    }

    private void getImgView(int img_src, ImageView img_view) {
        Glide
                .with(this)
                .load(img_src)
                .into(img_view);
    }


    @Override
    protected void onResume() {
        super.onResume();
        theme_music.start();
        theme_music.setLooping(true);
        showPlayerNameLayout();
        if(callBack_score!=null){
            if (scoreChanged){
                scoreChanged=false;
                updateScoreGame();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        theme_music.pause();
    }

    private void updateScoreGame() {
        LocationPlayerTrack  locationServis = new LocationPlayerTrack(Activity_Menu.this);
        if(locationServis.canGetLocation){
            latitude = locationServis.getLatitude();
            longitude = locationServis.getLongitude();
            Log.d("taffff", "updateScoreGame: "+latitude);
            Log.d("taffff", "updateScoreGame: "+longitude);

            Toast.makeText(getApplicationContext(), "THE PLAYER SAVED", Toast.LENGTH_LONG).show();
        }
        else {
            locationServis.showSettingsAlert();
            latitude = locationServis.getLatitude();
            longitude = locationServis.getLongitude();

        }

        String js = MSPV3.getMe().getString("MY_DB", "");
        myDB = new Gson().fromJson(js, MyDB.class);
        for (int i = 0; i <myDB.getMyScores().size() ; i++) {
            if(myDB.getMyScores().get(i).getPlayerName().equals(playerName)){
                if(myDB.getMyScores().get(i).getScore()<playerScore){
                    myDB.getMyScores().get(i).setScore(playerScore).setLat(latitude).setLon(longitude);
                }else{
                    myDB.getMyScores().get(i).setLat(latitude).setLon(longitude);
                }
            }
        }
        if(!playerExist){
            myDB.getMyScores().add(new Player().setPlayerName(playerName).setScore(playerScore).setLat(latitude).setLon(longitude));
        }
        playerExist=false;

        Collections.sort(myDB.getMyScores(), new ScoresSortHelper());
        String json = new Gson().toJson(myDB);
        MSPV3.getMe().putString("MY_DB", json);

    }

    protected void onStart() {
        super.onStart();
        theme_music.start();
        theme_music.setLooping(true);

    }

    @Override
    protected void onStop() {
        theme_music.pause();
        super.onStop();
    }

}



