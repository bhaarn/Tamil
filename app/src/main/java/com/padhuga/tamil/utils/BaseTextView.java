package com.padhuga.tamil.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.padhuga.tamil.activities.BaseActivity;

public class BaseTextView extends AppCompatTextView {

    public BaseTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        super.setTextSize(((BaseActivity)context).retrieveFontSize());
        super.setTextColor(Color.parseColor(((BaseActivity)context).retrieveTextTheme()));
    }

    public BaseTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setTextSize(((BaseActivity)context).retrieveFontSize());
        super.setTextColor(Color.parseColor(((BaseActivity)context).retrieveTextTheme()));
    }

    public BaseTextView(Context context) {
        super(context);
        super.setTextSize(((BaseActivity)context).retrieveFontSize());
        super.setTextColor(Color.parseColor(((BaseActivity)context).retrieveTextTheme()));
    }
}
