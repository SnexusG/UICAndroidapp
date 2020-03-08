package com.example.digitaluic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class WelcomeMessage extends AppCompatDialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Welcome!").setMessage("Hello, we at UIC believe in innovating new and better alternatives for learning, ensuring maximum productivity and conceptual understanding.\nThis is our first step in that direction.\nPlease feel free to contact us at uic.contactus@gmain.com!\nThank you!")
                .setNegativeButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), LoginPage.class);
                        startActivity(intent);
                    }
                });

        return builder.create();
    }
}
