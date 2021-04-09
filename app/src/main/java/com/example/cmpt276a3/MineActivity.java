package com.example.cmpt276a3;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cmpt276a3.model.Mine;
import com.example.cmpt276a3.model.MineManager;

public class MineActivity extends AppCompatActivity {
    //private static final int NUM_ROWS = 4;
    //private static final int NUM_COLS = 7;
    //Button buttons[][] = new Button [NUM_ROWS][NUM_COLS];
    Button buttons[][];
    private int totalScans = 0;
    private int totalFound = 0;
    MineManager manager;


    private static final String EXTRA_MESSAGE = "Extra-Message";

    public static Intent makeLaunchIntent(Context c, String message) {
        Intent intent = new Intent(c, MineActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        populateButtons();
        manager = MineManager.getInstance();
        TextView totalMines = findViewById(R.id.textTotalMines);
        totalMines.setText("" + manager.getCount());
    }

    private void populateButtons() {
        manager = MineManager.getInstance();
        buttons = new Button[manager.getRows()][manager.getColumns()];
        manager.setMines();

        TableLayout table = (TableLayout) findViewById(R.id.MineSeeker);
        for (int row = 0; row < manager.getRows(); row++){
            TableRow tableRow = new TableRow(this);
            tableRow.setLayoutParams(new TableLayout.LayoutParams(
                    TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.MATCH_PARENT,
                    1.0f));
            table.addView(tableRow);
            for (int col = 0; col < manager.getColumns(); col++){
                final int FINAL_COL = col;
                final int FINAL_ROW = row;

                Button button = new Button(this);
                button.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.MATCH_PARENT,
                        1.0f));

//                button.setText("" + row + "," + col);
                button.setPadding(0,0,0,0);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        gridButtonClicked(FINAL_COL, FINAL_ROW);
                    }


                });
                tableRow.addView(button);
                buttons [row][col] = button;
            }
        }
    }

    private void gridButtonClicked(int col, int row) {
        final MediaPlayer virus_alert = MediaPlayer.create(MineActivity.this, R.raw.alert);

        final int FINAL_COL = col;
        final int FINAL_ROW = row;
        manager = MineManager.getInstance();
        //Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show();
        Button button = buttons [row][col];
        button.setPadding(0,0,0,0);
        //lock button sizes
        lockButtonSizes();

        Mine clicked = manager.getMines(FINAL_ROW,FINAL_COL);

        if (clicked.isPresent()){
            if (!clicked.isReveal()){
                virus_alert.start();
                totalFound = totalFound + 1;
                manager.getMines(FINAL_ROW,FINAL_COL).setReveal(true);
                //scale image to button
                int newWidth = button.getWidth();
                int newHeight = button.getHeight();
                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.virus);
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                Resources resource = getResources();
                button.setBackground(new BitmapDrawable(resource, scaledBitmap));
                button.setText("");
                updata_col_row(FINAL_COL,FINAL_ROW,"mine");
                update_count();
            }
            else {
                if(!clicked.isText_reveal()){
                    totalScans = totalScans + 1;
                    updata_col_row(FINAL_COL,FINAL_ROW,"text");
                    update_count();
                }
            }
        }
        else {
            if (clicked.isReveal()){
                updata_col_row(FINAL_COL,FINAL_ROW,"text");
                update_count();
            }
            else{
                totalScans = totalScans + 1;
                manager.getMines(FINAL_ROW,FINAL_COL).setReveal(true);
                updata_col_row(FINAL_COL,FINAL_ROW,"text");
                update_count();
            }
        }
        //change text
        /***if (clicked.isText()) {
            updata_col_row(FINAL_COL,FINAL_ROW,"text");
        }***/

    }


    private void update_count() {
        manager = MineManager.getInstance();
        TextView mineFound = findViewById(R.id.textfoundnumber);
        mineFound.setText("" + totalFound + " ");

        TextView scanUsed = findViewById(R.id.textScanedNumber);
        scanUsed.setText("" + totalScans);

        if (totalFound >= manager.getCount()){
            gameOver();
        }
    }

    private void updata_col_row(int col,int row, String s) {
        final int FINAL_COL = col;
        final int FINAL_ROW = row;
        manager = MineManager.getInstance();
        if (s == "text" && !manager.getMines(FINAL_ROW, FINAL_COL).isText_reveal()) {
            manager.getMines(FINAL_ROW, FINAL_COL).setText_reveal(true);
        }
        for (int j = 0; j < manager.getColumns(); j++) {
            if (manager.getMines(FINAL_ROW, j).isText_reveal()) {
                Button button_text = buttons [FINAL_ROW][j];
                int remain = 0;
                for (int i = 0; i < manager.getColumns(); i++) {
                    if (manager.getMines(FINAL_ROW, i).isPresent() && manager.getMines(FINAL_ROW, i).isReveal() == false) {
                        remain++;
                    }
                }
                for (int i = 0; i < manager.getRows(); i++) {
                    if (manager.getMines(i, j).isPresent() && manager.getMines(i, j).isReveal() == false) {
                        remain++;
                    }
                }
                button_text.setText("" + remain);
                button_text.setTextColor(Color.parseColor("#ffffff"));
            }
        }
        for (int j = 0; j < manager.getRows(); j++) {
            if (manager.getMines(j, FINAL_COL).isText_reveal()) {
                Button button_text = buttons [j][FINAL_COL];
                int remain = 0;
                for (int i = 0; i < manager.getColumns(); i++) {
                    if (manager.getMines(j, i).isPresent() && manager.getMines(j, i).isReveal() == false) {
                        remain++;
                    }
                }
                for (int i = 0; i < manager.getRows(); i++) {
                    if (manager.getMines(i, FINAL_COL).isPresent() && manager.getMines(i, FINAL_COL).isReveal() == false) {
                        remain++;
                    }
                }
                button_text.setText("" + remain);
                button_text.setTextColor(Color.parseColor("#ffffff"));
            }
        }
    }

    private void lockButtonSizes() {
        manager = MineManager.getInstance();
        for (int row = 0; row < manager.getRows(); row++){
            for (int col = 0; col < manager.getColumns(); col++){
                Button button = buttons [row][col];
                int width = button.getWidth();
                button.setMinWidth(width);
                button.setMaxWidth(width);

                int height = button.getHeight();
                button.setMinHeight(height);
                button.setMaxHeight(height);
            }
        }
    }

    private void gameOver() {
        FragmentManager fragment_manager = getSupportFragmentManager();
        GameOverFragment dialog= new GameOverFragment();
        dialog.show(fragment_manager, "Game Over");
    }


}
