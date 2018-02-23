package com.padhuga.tamil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.padhuga.tamil.R;
import com.padhuga.tamil.adapters.ExpandableListViewAdapter;
import com.padhuga.tamil.utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainFragment extends Fragment {
    private int position;

    public static MainFragment newInstance(int position) {
        MainFragment mainFragment = new MainFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_POSITION, position);
        mainFragment.setArguments(args);
        return mainFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        if (getArguments() != null) {
            position = getArguments().getInt(Constants.ARG_SECTION_POSITION);
        }
        initializeExpandableList(rootView);
        return rootView;
    }

    private void initializeExpandableList(View rootView) {
        List<String> listDataHeader = new ArrayList<>();
        HashMap<String, List<String>> listDataChild = new HashMap<>();
        ArrayList<String> st;
        for (int i = 0; i < (((BaseActivity) getActivity()).parentModel.data.type.get(position).type.size()); i++) {
            listDataHeader.add(((BaseActivity) getActivity()).parentModel.data.type.get(position).type.get(i).title);
            st = new ArrayList<>();
            for (int j = 0; j < ((BaseActivity) getActivity()).parentModel.data.type.get(position).type.get(i).type.size(); j++) {
                st.add(j, ((BaseActivity) getActivity()).parentModel.data.type.get(position).type.get(i).type.get(j).title);
            }
            listDataChild.put(listDataHeader.get(i), st);
        }

        final ExpandableListView expandableListView = rootView.findViewById(R.id.expandableListView);
        ExpandableListAdapter listAdapter = new ExpandableListViewAdapter(getContext(), listDataHeader, listDataChild);
        expandableListView.setAdapter(listAdapter);
        CardView expandableListViewParent = rootView.findViewById(R.id.expandableListViewParent);
        expandableListViewParent.setVisibility(View.VISIBLE);

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                setupUI(groupPosition, childPosition);
                return false;
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if (expandableListView.getExpandableListAdapter().getChildrenCount(groupPosition) == 0) {
                    setupParentUI(groupPosition);
                }
                return false;
            }
        });
    }

    private void setupUI(int groupPosition, int childPosition) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(Constants.ARG_SECTION_POSITION, position);
        intent.putExtra(Constants.ARG_PARENT_POSITION, groupPosition);
        intent.putExtra(Constants.ARG_CHILD_POSITION, childPosition);
        startActivity(intent);
    }

    private void setupParentUI(int groupPosition) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(Constants.ARG_SECTION_POSITION, position);
        intent.putExtra(Constants.ARG_PARENT_POSITION, groupPosition);
        startActivity(intent);
    }
}
