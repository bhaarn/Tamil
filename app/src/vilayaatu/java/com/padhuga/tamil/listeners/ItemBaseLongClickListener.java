package com.padhuga.tamil.listeners;

import android.content.ClipData;
import android.os.Build;
import android.view.View;
import android.widget.AdapterView;

import com.padhuga.tamil.adapters.ItemBaseAdapter;
import com.padhuga.tamil.models.Item;
import com.padhuga.tamil.models.PassObject;

import java.util.List;

public class ItemBaseLongClickListener implements AdapterView.OnItemLongClickListener {

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view,
                                   int position, long id) {
        Item selectedItem = (Item) (parent.getItemAtPosition(position));

        ItemBaseAdapter associatedAdapter = (ItemBaseAdapter) (parent.getAdapter());
        List<Item> associatedList = associatedAdapter.getList();

        PassObject passObj = new PassObject(view, selectedItem, associatedList);

        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            view.startDragAndDrop(data, shadowBuilder, passObj, 0);
        } else {
            view.startDrag(data, shadowBuilder, passObj, 0);
        }
        return true;
    }
}
