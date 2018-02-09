package com.padhuga.tamil.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.padhuga.tamil.R;
import com.padhuga.tamil.listeners.ItemBaseDragListener;
import com.padhuga.tamil.models.Item;
import com.padhuga.tamil.models.ItemBaseViewHolder;
import com.padhuga.tamil.models.Results;

import java.util.ArrayList;
import java.util.List;

public class ItemBaseAdapter extends BaseAdapter {

    private final Context context;
    private final List<Item> list;
    private final String layoutType;
    private ArrayList<Results> results;

    public ItemBaseAdapter(Context context, List<Item> list, String layoutType, ArrayList<Results> results){
        this.context = context;
        this.list = list;
        this.layoutType = layoutType;
        this.results = results;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Item> getList(){
        return list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        final ViewGroup nullParent = null;

        // reuse views
        if (rowView == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            rowView = inflater.inflate(R.layout.row, nullParent);

            ItemBaseViewHolder viewHolder = new ItemBaseViewHolder();
            viewHolder.text = rowView.findViewById(R.id.rowTextView);
            rowView.setTag(viewHolder);
        }

        ItemBaseViewHolder holder = (ItemBaseViewHolder) rowView.getTag();
        switch (layoutType) {
            case "Icon Text":
                holder.text.setCompoundDrawablesWithIntrinsicBounds(list.get(position).ItemDrawable, null, null, null);
                holder.text.setText(list.get(position).ItemString);
                break;
            case "Icon":
                holder.text.setCompoundDrawablesWithIntrinsicBounds(list.get(position).ItemDrawable, null, null, null);
                break;
            case "Text":
                holder.text.setText(list.get(position).ItemString);
                break;
            default:
                break;
        }

        rowView.setOnDragListener(new ItemBaseDragListener(list.get(position), context, results));

        return rowView;
    }

}
