package com.example.cmpt276a3;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatDialogFragment;

public class GameOverFragment extends AppCompatDialogFragment{
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v = LayoutInflater.from(getActivity()).inflate(R.layout.game_over, null);
        final ImageView gameoverImg = v.findViewById(R.id.imageGameOver);
        gameoverImg.setImageDrawable(getResources().getDrawable(R.drawable.game_over));

        DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
            }
        };

        return new AlertDialog.Builder(getActivity())
                .setTitle("Game Over")
                .setView(v)
                .setPositiveButton(android.R.string.ok, listener)
                .create();

    }
}
