/**
 * Manage the mines. Can be used to set up the mine map based on
 * user's option.
 */
package com.example.cmpt276a3.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MineManager {
    private int rows = 4;
    private int columns = 6;
    private int count = 6;
    private Mine[][] mines;
    private int k = 0;

    //singleton
    private MineManager() {
    }
    private static MineManager instance;

    public static MineManager getInstance() {
        if (instance == null) {
            instance = new MineManager();
        }
        return instance;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }


    public Mine getMines(int row, int col) {
        return mines[row][col];
    }


    public void setMines(){
        mines = new Mine [rows][columns];
        ArrayList<Integer> mineMap = new ArrayList<>();
        for (int i = 0; i < rows * columns; i++) {
            mineMap.add(i);
        }
        Collections.shuffle(mineMap);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int k = 0;
                boolean done = false;
                for (int index : mineMap) {
                    if (k < count && done == false) {
                        if (index == (i * columns) + j) {
                            mines[i][j] = new Mine(false, true, false , false);
                            k = k + 1;
                            done = true;
                        }
                        else {
                            mines[i][j] = new Mine(false, false, true, false);
                            k = k + 1;
                        }
                    }

                }
            }
        }

    }



}
