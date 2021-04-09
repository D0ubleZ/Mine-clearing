package com.example.cmpt276a3;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class HelpActivity extends AppCompatActivity {
    private static final String EXTRA_MESSAGE = "Extra-Message";

    public static Intent makeLaunchIntent(Context c, String message) {
        Intent intent = new Intent(c, HelpActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView author = findViewById(R.id.textAboutAuthor);
        author.setMovementMethod(LinkMovementMethod.getInstance());

        TextView logo = findViewById(R.id.textLogo);
        logo.setMovementMethod(LinkMovementMethod.getInstance());

        TextView background = findViewById(R.id.textBackground);
        background.setMovementMethod(LinkMovementMethod.getInstance());

        TextView audio = findViewById(R.id.textAudio);
        audio.setMovementMethod(LinkMovementMethod.getInstance());

        TextView gameover = findViewById(R.id.textGameOver);
        gameover.setMovementMethod(LinkMovementMethod.getInstance());

    }

}
