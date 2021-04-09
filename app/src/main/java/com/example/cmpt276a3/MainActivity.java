package com.example.cmpt276a3;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.example.cmpt276a3.model.MineManager;

public class MainActivity extends AppCompatActivity {

    Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = WelcomeScreenActivity.makeLaunchIntent(MainActivity.this, "Welcome123");
        startActivity(intent);

        Button btnGame = findViewById(R.id.buttonGame);
        refresh();
        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MineActivity.makeLaunchIntent(MainActivity.this, "Mine");
                startActivity(intent);
            }
        });

        Button btnHelp = findViewById(R.id.buttonHelp);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = HelpActivity.makeLaunchIntent(MainActivity.this, "Help");
                startActivity(intent);
            }
        });

        Button btnOption = findViewById(R.id.buttonOption);
        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = OptionActivity.makeLaunchIntent(MainActivity.this, "Option");
                startActivity(intent);
            }
        });

    }

    private void refresh() {
        MineManager manager;
        manager = MineManager.getInstance();
        int mines = OptionActivity.getMinesInstalled(this);
        manager.setCount(mines);
        int row = OptionActivity.getRowInstalled(this);
        manager.setRows(row);
        int col = OptionActivity.getColInstalled(this);
        manager.setColumns(col);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh();
    }
}
