package com.padhuga.tamil.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.padhuga.tamil.R;
import com.padhuga.tamil.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends ListFragment {
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
        initializeList(rootView);
        return rootView;
    }

    private void initializeList(View rootView) {
        List<String> listData = new ArrayList<>();
        for (int i = 0; i < (((BaseActivity) getActivity()).parentModel.data.type.get(position).type.size()); i++) {
            listData.add(((BaseActivity) getActivity()).parentModel.data.type.get(position).type.get(i).title);
        }
        ListView listView = rootView.findViewById(android.R.id.list);
        if (getContext() != null) {
            ArrayAdapter<String> listAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, listData);
            listView.setAdapter(listAdapter);
            CardView listViewParent = rootView.findViewById(R.id.listViewParent);
            listViewParent.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        setupUI(position);
    }

    private void setupUI(int childPosition) {
        Intent intent = new Intent(getActivity(), DetailsActivity.class);
        intent.putExtra(Constants.ARG_SECTION_POSITION, position);
        intent.putExtra(Constants.ARG_CHILD_POSITION, childPosition);
        startActivity(intent);
    }
}
