package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class PopErrorAddStudentToCenter extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Error!")
                .setMessage("Ja ets registrat en aquest centre")
                .setPositiveButton("Ok", (dialog, which) -> {
                });
        return builder.create();
    }
}
