package com.padhuga.tamil.utils;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.padhuga.tamil.R;
import com.padhuga.tamil.activities.LinearLayoutAbsListView;
import com.padhuga.tamil.adapters.ItemBaseAdapter;
import com.padhuga.tamil.listeners.ItemBaseLongClickListener;
import com.padhuga.tamil.listeners.ItemMyDragListener;
import com.padhuga.tamil.models.Item;
import com.padhuga.tamil.models.Results;

import java.util.ArrayList;
import java.util.List;

public class ItemGenerator {

    private final Context context;
    private List<Item> items1;
    private List<Item> items2;
    private List<Item> items3;
    private List<Item> items4;
    private List<Item> items5;
    private List<Item> items6;

    public ItemGenerator(Context context) {
        this.context = context;
    }

    public void init(ViewGroup rootView, int parentCount, String layoutType, ArrayList<String> parentHeading, ArrayList<Integer> childItems, ArrayList<Results> results) {
        LinearLayout parentPane = rootView.findViewById(R.id.parent_pane);
        LinearLayout parentPanel1 = rootView.findViewById(R.id.parent_panel1);
        LinearLayout parentPanel2 = rootView.findViewById(R.id.parent_panel2);
        int[] areaIds = new int[]{R.id.pane1, R.id.pane2, R.id.pane3, R.id.pane4, R.id.pane5, R.id.pane6};
        int[] listViewIds = new int[]{R.id.list_view1, R.id.list_view2, R.id.list_view3, R.id.list_view4, R.id.list_view5, R.id.list_view6};
        int[] textViewIds = new int[]{R.id.heading1, R.id.heading2, R.id.heading3, R.id.heading4, R.id.heading5, R.id.heading6};

        ArrayList<LinearLayoutAbsListView> areas = new ArrayList<>();
        ArrayList<ListView> listViews = new ArrayList<>();
        ArrayList<TextView> textViews = new ArrayList<>();

        initItems(childItems);
        switch (parentCount) {
            case 1:
                parentPanel2.setVisibility(View.VISIBLE);
                parentPanel2.setWeightSum(1);
                break;
            case 2:
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);
                layoutParams.weight = 1;
                parentPane.setOrientation(LinearLayout.HORIZONTAL);
                parentPanel1.setLayoutParams(layoutParams);
                parentPanel2.setLayoutParams(layoutParams);
                parentPanel1.setVisibility(View.VISIBLE);
                parentPanel2.setVisibility(View.VISIBLE);
                break;
            case 3:
            case 4:
            case 5:
            case 6:
                parentPanel1.setVisibility(View.VISIBLE);
                parentPanel2.setVisibility(View.VISIBLE);
                break;
            default:
                break;
        }
        for (int i = 0; i < parentCount; i++) {
            textViews.add((TextView) rootView.findViewById(textViewIds[i]));
            textViews.get(i).setText(parentHeading.get(i));
            listViews.add((ListView) rootView.findViewById(listViewIds[i]));
            listViews.get(i).setAdapter(new ItemBaseAdapter(context, getItems(i), layoutType, results));
            listViews.get(i).setOnItemLongClickListener(new ItemBaseLongClickListener());
            areas.add((LinearLayoutAbsListView) rootView.findViewById(areaIds[i]));
            areas.get(i).setVisibility(View.VISIBLE);
            areas.get(i).setOnDragListener(new ItemMyDragListener(results));
            areas.get(i).setAbsListView(listViews.get(i));
        }
    }

    private List<Item> getItems(int itemPosition) {
        switch (itemPosition) {
            case 0:
                return items1;
            case 1:
                return items2;
            case 2:
                return items3;
            case 3:
                return items4;
            case 4:
                return items5;
            case 5:
                return items6;
            default:
                return null;
        }
    }

    private void initItems(ArrayList<Integer> childItems) {
        items1 = new ArrayList<>();
        items2 = new ArrayList<>();
        items3 = new ArrayList<>();
        items4 = new ArrayList<>();
        items5 = new ArrayList<>();
        items6 = new ArrayList<>();

        TypedArray arrayDrawable = context.getResources().obtainTypedArray(R.array.icon);
        TypedArray arrayText = context.getResources().obtainTypedArray(R.array.text);

        for (int i = 0; i < childItems.size(); i++) {
            Drawable d = arrayDrawable.getDrawable(childItems.get(i) - 1);
            String s = arrayText.getString(childItems.get(i) - 1);
            Item item = new Item(d, s, i + 1);
            items1.add(item);
        }

        arrayDrawable.recycle();
        arrayText.recycle();
    }

    private void getResultStrings(ArrayList<Results> results) {
        TypedArray arrayText = context.getResources().obtainTypedArray(R.array.text);
        for (int i = 0; i < results.size(); i++) {
            String s = arrayText.getString(results.get(i).parent1.get(i) - 1);
        }
        arrayText.recycle();
    }
}
