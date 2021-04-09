package com.example.cmpt276a3;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OptionActivity extends AppCompatActivity {

    private static final String EXTRA_MESSAGE = "Extra-Message";

    public static Intent makeLaunchIntent(Context c, String message) {
        Intent intent = new Intent(c, OptionActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createRowRadioButtons();
        createColRadioButtons();
        createMinesRadioButtons();

        int savedMines = getMinesInstalled(this);
        Toast.makeText(OptionActivity.this,"Saved mines " + savedMines, Toast.LENGTH_SHORT).show();

        int savedRow = getRowInstalled(this);
        Toast.makeText(OptionActivity.this,"Saved row " + savedRow, Toast.LENGTH_SHORT).show();

        int savedCol = getColInstalled(this);
        Toast.makeText(OptionActivity.this,"Saved col " + savedCol, Toast.LENGTH_SHORT).show();
    }

    private void createMinesRadioButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.mines_option);

        int[] mines = getResources().getIntArray(R.array.mines_size);

        for (int i = 0; i < mines.length; i++){
            final int mine = mines[i];

            RadioButton button = new RadioButton(this);
            button.setText(mine + "");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionActivity.this,"You clicked mines" + mine, Toast.LENGTH_SHORT).show();

                    saveMinesInstalled(mine);
                }
            });
            group.addView(button);

            if(mine == getMinesInstalled(this)){
                button.setChecked(true);
            }
        }
    }
    private static final String MINES = "Mines";
    private static final String MINE = "mines";

    private void saveMinesInstalled(int mine) {
        SharedPreferences prefs = this.getSharedPreferences(MINES, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(MINE,mine);
        editor.apply();
    }

    static public int getMinesInstalled(Context context){
        SharedPreferences prefs = context.getSharedPreferences(MINES,MODE_PRIVATE);

        int default_mines = context.getResources().getInteger(R.integer.default_mines);
        return prefs.getInt(MINE,default_mines);
    }

    private void createRowRadioButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.row_option);

        int[] rows = getResources().getIntArray(R.array.row_size);

        for (int i = 0; i < rows.length; i++){
            final int row = rows[i];

            RadioButton button = new RadioButton(this);
            button.setText(row + "");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionActivity.this,"You clicked row" + row, Toast.LENGTH_SHORT).show();

                    saveRowInstalled(row);
                }
            });

            group.addView(button);

            if(row == getRowInstalled(this)){
                button.setChecked(true);
            }
        }
    }

    private static final String ROWS = "Rows";
    private static final String ROW = "rows";
    private void saveRowInstalled(int row) {
        SharedPreferences prefs = this.getSharedPreferences(ROWS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(ROW,row);
        editor.apply();
    }
    static public int getRowInstalled(Context context){
        SharedPreferences prefs = context.getSharedPreferences(ROWS,MODE_PRIVATE);

        int default_row = context.getResources().getInteger(R.integer.default_row);
        return prefs.getInt(ROW,default_row);
    }

    private void createColRadioButtons() {
        RadioGroup group = (RadioGroup) findViewById(R.id.col_option);

        int[] cols = getResources().getIntArray(R.array.col_size);

        for (int i = 0; i < cols.length; i++){
            final int col = cols[i];

            RadioButton button = new RadioButton(this);
            button.setText(col + "");

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(OptionActivity.this,"You clicked col" + col, Toast.LENGTH_SHORT).show();
                    saveColInstalled(col);
                }
            });

            group.addView(button);

            if(col == getColInstalled(this)){
                button.setChecked(true);
            }
        }
    }

    private static final String COLS = "Cols";
    private static final String COL = "cols";

    private void saveColInstalled(int col) {
        SharedPreferences prefs = this.getSharedPreferences(COLS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(COL,col);
        editor.apply();
    }
    static public int getColInstalled(Context context){
        SharedPreferences prefs = context.getSharedPreferences(COLS,MODE_PRIVATE);

        int default_col = context.getResources().getInteger(R.integer.default_col);
        return prefs.getInt(COL,default_col);
    }

}