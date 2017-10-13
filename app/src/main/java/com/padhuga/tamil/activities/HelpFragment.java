package com.padhuga.tamil.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padhuga.tamil.R;
import com.padhuga.tamil.utils.BaseTextView;

public class HelpFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        BaseTextView helpText = rootView.findViewById(R.id.help_text);
        helpText.setText(R.string.help_text);
        return rootView;
    }
}
