package com.example.maths;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
public class score extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        ImageButton btn_share = (ImageButton) findViewById(R.id.btn_share);

                TextView tv = (TextView) findViewById(R.id.score);
                int score = getIntent().getIntExtra("score", 0);
                tv.setText("Current Score : " + score);

        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Just Maths - Fun way to learn Maths. http://www.play.google.com");
                startActivity(intent);
            }
        });
        }
    }

