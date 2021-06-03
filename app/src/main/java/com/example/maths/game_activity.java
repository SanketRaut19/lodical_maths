package com.example.maths;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import java.util.Random;

public class game_activity extends AppCompatActivity {


    TextView txtViewQuestion;
    TextView txtViewResult;
    TextView txtTime;
    TextView txtScore;
    ImageButton btnCorrect;
    ImageButton btnIncorrect;
    boolean isResultCorrect;
    int seconds = 59;
    private int score = 0;
    private boolean stopTimer = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_activity);

                txtViewQuestion = (TextView) findViewById(R.id.txt_view_question);
                txtViewResult = (TextView) findViewById(R.id.txt_view_result);
                txtTime = (TextView) findViewById(R.id.txt_time);
                txtScore = (TextView) findViewById(R.id.txt_score);
                btnCorrect = (ImageButton) findViewById(R.id.btn_correct);
                btnIncorrect = (ImageButton) findViewById(R.id.btn_incorrect);
                timer();
                btnCorrect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        verifyAnswer(true);
                    }
                });
                btnIncorrect.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        verifyAnswer(false);
                    }
                });
                generateQuestion();
            }

            private void generateQuestion() {
                isResultCorrect = true;
                Random random = new Random();
                int a = random.nextInt(100);
                int b = random.nextInt(100);
                int result = a + b;
                float f = random.nextFloat();
                if (f > 0.5f) {
                    result = random.nextInt(100);
                    isResultCorrect = false;
                }
                txtViewQuestion.setText(a + "+" + b);
                txtViewResult.setText("=" + result);
            }

            public void verifyAnswer(boolean answer) {
                if (answer == isResultCorrect) {
                    score += 5;
                    txtScore.setText("SCORE: " + score);
                } else {
                    Vibrator vibrator = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(100);
                }
                generateQuestion();
            }

            private void timer() {
                final Handler handler = new Handler();
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        txtTime.setText("TIME :" + seconds);
                        seconds--;
                        if (seconds < 0) {
                            Intent i = new Intent(game_activity.this, score.class);
                            i.putExtra("score", score);
                            startActivity(i);
                            stopTimer = true;
                        }
                        if (stopTimer == false) {
                            handler.postDelayed(this, 1000);
                        }
                    }
                });
            }

            @Override
            protected void onPause() {
                super.onPause();
                stopTimer = false;
                finish();
            }
        }
