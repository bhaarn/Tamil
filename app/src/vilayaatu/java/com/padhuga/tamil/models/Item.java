package com.padhuga.tamil.models;

import android.graphics.drawable.Drawable;

//items stored in ListView
public class Item {
    public final Drawable ItemDrawable;
    public final String ItemString;
    public int ItemPlaceValue;

    public Item(Drawable drawable, String t, int placeValue) {
        ItemDrawable = drawable;
        ItemString = t;
        ItemPlaceValue = placeValue;
    }
}
