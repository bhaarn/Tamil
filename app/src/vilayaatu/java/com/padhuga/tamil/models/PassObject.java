package com.padhuga.tamil.models;

import android.view.View;

import java.util.List;

//objects passed in Drag and Drop operation
public class PassObject {
   public final View view;
   public final Item item;
   public final List<Item> srcList;

   public PassObject(View v, Item i, List<Item> s) {
       view = v;
       item = i;
       srcList = s;
   }
}
