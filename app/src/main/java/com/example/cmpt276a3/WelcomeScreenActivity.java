package com.example.cmpt276a3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeScreenActivity extends AppCompatActivity {
    Animation rotation;
    Animation slide_down;
    Animation slide_left;
    ImageView imageWelcome;
    TextView mineSeeker;
    TextView by;
    Timer endWelcome;

    private static final String EXTRA_MESSAGE = "Extra-Message";

    public static Intent makeLaunchIntent(Context c, String message) {
        Intent intent = new Intent(c, WelcomeScreenActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imageWelcome = findViewById(R.id.imageWelcome);
        imageWelcome.setImageResource(R.drawable.logo);

        mineSeeker = findViewById(R.id.textWelcomeMine);
        by = findViewById(R.id.textAuthor);


        rotateAnimation();
        slideAnimation();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        endWelcome = new Timer();
        endWelcome.schedule(new TimerTask() {
            @Override
            public void run() {
                finish();
            }
        }, 10000);
    }

    private void slideAnimation() {
        slide_down = AnimationUtils.loadAnimation(this,R.anim.slide_down);
        mineSeeker.startAnimation(slide_down);

        slide_left = AnimationUtils.loadAnimation(this,R.anim.slide_left);
        by.startAnimation(slide_left);

    }

    private void rotateAnimation() {
        rotation = AnimationUtils.loadAnimation(this,R.anim.rotation);
        imageWelcome.startAnimation(rotation);
    }

}
