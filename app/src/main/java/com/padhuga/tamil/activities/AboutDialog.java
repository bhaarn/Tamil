package com.padhuga.tamil.activities;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.padhuga.tamil.R;
import com.padhuga.tamil.utils.Constants;

class AboutDialog extends AlertDialog {

    private TextView appNameText;
    private TextView aboutText;
    private TextView versionText;

    AboutDialog(Context context) {
        super(context);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        assert inflater != null;
        View layout = inflater.inflate(R.layout.dialog_about, null);

        aboutText = layout.findViewById(android.R.id.text2);
        versionText = layout.findViewById(android.R.id.text1);
        appNameText = layout.findViewById(android.R.id.title);
        ImageView mIconView = layout.findViewById(android.R.id.icon);
        setView(layout);
        loadAbout();
        setTitle(R.string.action_about);
        mIconView.setOnClickListener(new View.OnClickListener() {
        int mClickCount = 0;
            @Override
            public void onClick(View v) {
                mClickCount++;
                if (mClickCount == 5) {
                    Toast.makeText(getContext(), R.string.pro_version_message, Toast.LENGTH_SHORT).show();
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(getContext()).edit();
                            edit.putBoolean(Constants.PREF_IS_PRO, true);
                            edit.apply();
                        }
                    }).start();
                }
            }
        });
        setButton(DialogInterface.BUTTON_POSITIVE, getContext().getString(R.string.ok), new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
    }

    private void loadAbout() {
        try {
            PackageInfo packageInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
            appNameText.setText(R.string.app_name);
            versionText.setText(getContext().getResources().getString(R.string.version_label, packageInfo.versionName));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        aboutText.setText(Html.fromHtml(getContext().getResources().getString(R.string.developer_info)));
    }
}
