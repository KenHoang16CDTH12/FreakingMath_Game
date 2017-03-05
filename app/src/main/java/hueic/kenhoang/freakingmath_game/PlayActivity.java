package hueic.kenhoang.freakingmath_game;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    // time to play each level
    private final int TIME_PLAY_EACH_LEVEL = 4000;
    // define background color of play screen
    private String[] arrColor = {"#f48fb1", "#ef9a9a", "#ce93d8", "#b39ddb", "#9fa8da", "#90caf9",
            "#81d4fa", "#80deea", "#80cbc4", "#a5d6a7", "#c5e1a5", "#e6ee9c"};
    // view on screen
    private TextView tvScore, tvFormular, tvResult;
    private ImageButton btnCorrect, btnWrong;
    private RelativeLayout layoutRelative;
    // timer
    private Timer timer;
    private TimerTask timerTask;
    // play score
    private int score = 0;
    // level model
    private LevelModel model;
    private Random rad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        // remove title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        // hide action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_play);
        //find view
        tvScore = (TextView) findViewById(R.id.tvScore);
        tvFormular = (TextView) findViewById(R.id.tvFormular);
        tvResult = (TextView) findViewById(R.id.tvResult);

        btnCorrect = (ImageButton) findViewById(R.id.btnCorrect);
        btnWrong = (ImageButton) findViewById(R.id.btnWrong);

        layoutRelative = (RelativeLayout) findViewById(R.id.layoutRelative);

        // handle Create
        btnCorrect.setOnClickListener(this);
        btnWrong.setOnClickListener(this);
        // create random
        rad = new Random();
        // generate the firt level
        model = GenerateLevel.generateLevel(1);
        // show level info on screen
        displayNewLevel(model);
        // create timer, timertask
        createTimerTask();
    }

    private void createTimerTask() {
        // create timer
        timer = new Timer();

        // create time task
        timerTask = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // show game over screen
                        showGameover(score);
                    }
                });
            }
        };
        // set time to run timertask
        timer.schedule(timerTask, TIME_PLAY_EACH_LEVEL);
    }

    private void showGameover(final int score) {
        // display buttons
        btnCorrect.setEnabled(false);
        btnWrong.setEnabled(false);
        //cancel timer
        cancelTimer();
        //show game over UI
        new AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage("Your score: "+score)
                .setPositiveButton(R.string.replay, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //enable  buttons
                        tvScore.setText("0");
                        btnCorrect.setEnabled(true);
                        btnWrong.setEnabled(true);
                        //Play again
                        PlayActivity.this.score = 0;
                        PlayActivity.this.nextLevel(score);
                    }
                })
                .setNegativeButton(R.string.home, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        //Back to home  activity
                        PlayActivity.this.finish();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void nextLevel(int score) {
        //update score
        tvScore.setText(String.valueOf(score));
        //cancer timer
        cancelTimer();
        //set new time for next level
        createTimerTask();
        //update ui
        model = GenerateLevel.generateLevel(score);
        displayNewLevel(model);
    }

    private void cancelTimer() {
        timerTask.cancel();
        timer.cancel();
    }

    private void displayNewLevel(LevelModel model) {
        tvFormular.setText(model.strOperator);
        tvResult.setText(model.strResullt);
         //set random background color
        int indexColor = rad.nextInt(arrColor.length);
        layoutRelative.setBackgroundColor(Color.parseColor(arrColor[indexColor]));
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btnWrong) {
            if (model.correctWrong == false) {
                score += 1;
                //next level
                nextLevel(score);
            } else {
                //show game over
                showGameover(score);
            }
        }
        if (id == R.id.btnCorrect) {
            if (model.correctWrong == true) {
                //continous next level
                score += 1;
                //next level
                nextLevel(score);
            } else {
                //show game over
                showGameover(score);
            }
        }
    }
}
