package com.padhuga.tamil.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.padhuga.tamil.R;
import com.padhuga.tamil.models.SearchRetriever;
import com.padhuga.tamil.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private ArrayList<SearchRetriever> searchRetriever = null;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_search, container, false);
        ListView searchResultsListView = rootView.findViewById(R.id.search_results_list);
        String query = getArguments().getString(Constants.ARG_QUERY_TEXT);
        final List<String> queryResults = new ArrayList<>();

        searchRetriever = ((BaseActivity)getActivity()).showSearchResults(query);
        for(int titleIndex = 0;titleIndex < searchRetriever.size(); titleIndex++) {
            queryResults.add(titleIndex, searchRetriever.get(titleIndex).getTitle());
        }
        ArrayAdapter<String> searchArrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, queryResults);
        searchResultsListView.setAdapter(searchArrayAdapter);

        searchResultsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View v, int position, long arg3) {
                setupUI(searchRetriever.get(position).getPosition(), searchRetriever.get(position).getGroupPosition(), searchRetriever.get(position).getChildPosition());
            }
        });
        return rootView;
    }

    private void setupUI(int position, int groupPosition, int childPosition) {
        Fragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();

        args.putInt(Constants.ARG_SECTION_POSITION, position);
        args.putInt(Constants.ARG_PARENT_POSITION, groupPosition);
        args.putInt(Constants.ARG_CHILD_POSITION, childPosition);

        detailsFragment.setArguments(args);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(android.R.id.content, detailsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}