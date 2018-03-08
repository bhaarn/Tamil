package com.padhuga.tamil.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.padhuga.tamil.R;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final List<String> expandableListViewHeader;
    private final HashMap<String, List<String>> expandableListViewItem;


    public ExpandableListViewAdapter(Context context, List<String> listDataHeader,
                                     HashMap<String, List<String>> listChildData) {
        this.context = context;
        this.expandableListViewHeader = listDataHeader;
        this.expandableListViewItem = listChildData;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.expandableListViewItem.get(this.expandableListViewHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final String childText = (String) getChild(groupPosition, childPosition);
        final ViewGroup nullParent = null;

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.expandable_list_view_item, nullParent);
        }

        TextView listViewItemText = convertView
                .findViewById(R.id.listViewItemText);
        listViewItemText.setText(childText);
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.expandableListViewItem.get(this.expandableListViewHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.expandableListViewHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.expandableListViewHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        final ViewGroup nullParent = null;
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.expandable_list_view_header, nullParent);
        }

        TextView listViewHeaderText = convertView
                .findViewById(R.id.listViewHeaderText);
        listViewHeaderText.setTypeface(null, Typeface.BOLD);
        listViewHeaderText.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}