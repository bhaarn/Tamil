package com.padhuga.tamil.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.InputMethodManager;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.padhuga.tamil.R;
import com.padhuga.tamil.models.ParentModel;
import com.padhuga.tamil.models.SearchRetriever;
import com.padhuga.tamil.utils.Constants;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    private String appName;
    private Menu menu;
    private SharedPreferences sharedPref;
    private int progRess;
    ParentModel parentModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleIntent(getIntent());
        appName = getPackageName();
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        parentModel = readJSONFromAssetsAndConvertTogson();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_app_search:
                performSearch(menu);
                return true;
            case R.id.action_app_rate:
                rateApp();
                return true;
            case R.id.action_font_size:
                changeFont();
                return true;
            case R.id.action_theme_style:
                ChangeTheme();
                return true;
            case R.id.action_feature_help:
                showHelp();
                return true;
            case R.id.action_app_share:
                shareApp();
                return true;
            case R.id.action_more_apps:
                moreApps();
                return true;
            case R.id.action_about:
                aboutUs();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

        private ParentModel readJSONFromAssetsAndConvertTogson() {
            ParentModel parentModel = null;
            try {
                InputStream is = getAssets().open(getResources().getString(R.string.json_file_name));
                int size = is.available();
                byte[] buffer = new byte[size];
                int bytesRead = is.read(buffer);
                is.close();
                String json = new String(buffer, getResources().getString(R.string.json_open_file_format));
                Gson gson = new Gson();
                parentModel  = gson.fromJson(json, ParentModel.class);
                Log.d("Json Bytes Read", Integer.toString(bytesRead));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return parentModel;
        }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                Boolean themeChange = data.getBooleanExtra(Constants.PREF_THEME_CHANGE, false);
                if(themeChange) {
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            recreate();
                        }
                    }, 100);
                }
            }
        }
    }

    private void shareApp() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, R.string.app_name);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_message)+appName);
        startActivity(Intent.createChooser(sharingIntent, "Share the application"));
    }

    private void moreApps() {
        startActivity(new Intent(
                Intent.ACTION_VIEW,
                Uri.parse(getString(R.string.play_more_apps))));
    }

    private void aboutUs() {
        new AboutDialog(this).show();
    }

    private void rateApp() {
        try {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.old_play_store)
                            + appName)));
        } catch (android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(getString(R.string.new_play_store)
                            + appName)));
        }
    }

    private void showHelp() {
        Fragment fragment = new HelpFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(android.R.id.content, fragment, fragment.getClass().getSimpleName()).addToBackStack(null).commit();
    }

    private void performSearch(Menu menu) {
        openKeyboardPreference();
        SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_app_search).getActionView();
        searchView.setSearchableInfo(
                searchManager != null ? searchManager.getSearchableInfo(getComponentName()) : null);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            Fragment searchFragment = new SearchFragment();
            Bundle args = new Bundle();
            args.putString(Constants.ARG_QUERY_TEXT, query);
            searchFragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, searchFragment, searchFragment.getClass().getSimpleName()).addToBackStack(null).commit();
        }
    }

    public ArrayList<SearchRetriever> showSearchResults(String query) {
        ArrayList<SearchRetriever> searchRetriever = new ArrayList<>();
        for (int position = 0; position < parentModel.data.type.size(); position++) {
            for (int groupPosition = 0; groupPosition < parentModel.data.type.get(position).type.size(); groupPosition++) {
                for (int childPosition = 0; childPosition < parentModel.data.type.get(position).type.get(groupPosition).type.size(); childPosition++) {
                    if (parentModel.data.type.get(position).type.get(groupPosition).type.get(childPosition).type.contains(query) ||
                            parentModel.data.type.get(position).type.get(groupPosition).type.get(childPosition).soothiram.contains(query) ||
                            parentModel.data.type.get(position).type.get(groupPosition).type.get(childPosition).desc.contains(query) ||
                            parentModel.data.type.get(position).type.get(groupPosition).type.get(childPosition).example.contains(query)) {
                        searchRetriever.add(new SearchRetriever(parentModel.data.type.get(position).type.get(groupPosition).type.get(childPosition).title,
                                position, groupPosition, childPosition));
                    }
                }
            }
        }
        return searchRetriever;
    }

    private void changeFont() {
        final AlertDialog.Builder fontSizeSelectorDialog = new AlertDialog.Builder(this);
        final SeekBar fontSizeSetter = new SeekBar(this);
        fontSizeSetter.setMax(Integer.parseInt(getResources().getString(R.string.font_size_max)));  // 14 18 22
        fontSizeSetter.setProgress(sharedPref.getInt(Constants.PREF_COMMON_TEXT_SIZE, 16));
        fontSizeSelectorDialog.setIcon(android.R.drawable.ic_menu_sort_alphabetically);
        fontSizeSelectorDialog.setTitle(R.string.font_size_setter);
        fontSizeSelectorDialog.setView(fontSizeSetter);

        fontSizeSetter.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progRess = progress;
            }

            public void onStartTrackingTouch(SeekBar arg0) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        fontSizeSelectorDialog.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface fontSizeSelectorDialog, int which) {
                        if (progRess != 0) {
                            storeFontSize(progRess);
                            recreate();
                        }
                        fontSizeSelectorDialog.dismiss();
                    }
                });

        fontSizeSelectorDialog.setNegativeButton(android.R.string.cancel,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface fontSizeSelectorDialog, int i) {
                        fontSizeSelectorDialog.dismiss();
                    }
                });


        fontSizeSelectorDialog.create();
        fontSizeSelectorDialog.show();
    }

    private void ChangeTheme() {
        Intent intent = new Intent(BaseActivity.this, PrefsActivity.class);
        startActivityForResult(intent, 1);
    }

    private void storeFontSize(int fontSize) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(Constants.PREF_COMMON_TEXT_SIZE, fontSize);
        editor.apply();
    }

    public int retrieveFontSize() {
        return sharedPref.getInt(Constants.PREF_COMMON_TEXT_SIZE, -1);
    }

    public String retrieveTextTheme() {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return "#" + Integer.toHexString(sharedPref.getInt("text_color_preference", ContextCompat.getColor(this, R.color.colorListViewHeaderText)));
    }

    public String retrieveParentCardTheme() {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return "#" + Integer.toHexString(sharedPref.getInt("parent_card_color_preference", ContextCompat.getColor(this, R.color.colorBottomBackground)));
    }

    public String retrieveChildCardTheme() {
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        return "#" + Integer.toHexString(sharedPref.getInt("child_card_color_preference", ContextCompat.getColor(this, R.color.colorBackground)));
    }

    private void openKeyboardPreference() {
        Toast.makeText(getApplicationContext(), R.string.tamil_keyboard_check, Toast.LENGTH_LONG).show();
        InputMethodManager imeManager = (InputMethodManager) getApplicationContext().getSystemService(INPUT_METHOD_SERVICE);
        if (imeManager != null) {
            imeManager.showInputMethodPicker();
        }
        Resources res = getBaseContext().getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale("ta_IN".toLowerCase());
        res.updateConfiguration(conf, dm);
        Log.i("inside onStart", "after ever");
    }

    void initializeAds(Activity activity) {
        MobileAds.initialize(this, getResources().getString(R.string.ad_id));
        AdView mAdView = activity.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .addTestDevice("2818610EB6FDBC046DB14EFB8EE05FEE")
                .build();
        mAdView.loadAd(adRequest);
        Boolean b = adRequest.isTestDevice(this);
        Log.d("Bharani", b.toString());
    }
}
