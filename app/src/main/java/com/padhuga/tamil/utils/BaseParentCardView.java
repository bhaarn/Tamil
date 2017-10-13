package com.padhuga.tamil.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;

import com.padhuga.tamil.activities.BaseActivity;

public class BaseParentCardView extends CardView{
    public BaseParentCardView(Context context) {
        super(context);
        super.setCardBackgroundColor(Color.parseColor(((BaseActivity)context).retrieveParentCardTheme()));
    }

    public BaseParentCardView(Context context, AttributeSet attrs) {
        super(context, attrs);
        super.setCardBackgroundColor(Color.parseColor(((BaseActivity)context).retrieveParentCardTheme()));
    }

    public BaseParentCardView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        super.setCardBackgroundColor(Color.parseColor(((BaseActivity)context).retrieveParentCardTheme()));
    }
}
