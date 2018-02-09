package com.padhuga.tamil.activities;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.LinearLayout;

public class LinearLayoutAbsListView extends LinearLayout {
	
	public AbsListView absListView;

	public LinearLayoutAbsListView(Context context) {
		super(context);
	}

	public LinearLayoutAbsListView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public LinearLayoutAbsListView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public void setAbsListView(AbsListView alv){
		absListView = alv;
	}

}
