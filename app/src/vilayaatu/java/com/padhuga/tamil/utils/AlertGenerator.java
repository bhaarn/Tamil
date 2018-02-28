package com.padhuga.tamil.utils;


import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.padhuga.tamil.R;

public class AlertGenerator {

    public void buildAlert(Context context, boolean result) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(R.string.dialog_title);

        if (result) {
            builder.setMessage(R.string.success_dialog_message);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
        } else {
            builder.setMessage(R.string.failure_dialog_message);
            builder.setPositiveButton(R.string.see_results, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                }
            });
        }
        builder.create();
        builder.show();
    }
}
