package com.padhuga.tamil.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padhuga.tamil.R;
import com.padhuga.tamil.models.Data;
import com.padhuga.tamil.models.Results;
import com.padhuga.tamil.utils.Constants;
import com.padhuga.tamil.utils.ItemGenerator;

import java.util.ArrayList;


public class DetailsFragment extends Fragment {

    public static DetailsFragment newInstance(int position, int childPosition) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_POSITION, position);
        args.putInt(Constants.ARG_CHILD_POSITION, childPosition);
        detailsFragment.setArguments(args);
        return detailsFragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_details, container, false);
        setData(rootView);
        return rootView;
    }

    private void setData(ViewGroup rootView) {
        if(getArguments() != null) {
            int position = getArguments().getInt(Constants.ARG_SECTION_POSITION);
            int childPosition = getArguments().getInt(Constants.ARG_CHILD_POSITION);
            Data data = getActivity() != null ? ((BaseActivity) getActivity()).parentModel.data.type.get(position).type.get(childPosition) : null;
            int parentCount = Integer.parseInt(data != null ? data.parentCount : null);
            String  layoutType = data != null ? data.parentType : null;
            ArrayList<Integer> childItems = data != null ? data.items : null;
            ArrayList<String> parentHeading = data != null ? data.parentHeading : null;
            ArrayList<Results> results = data != null ? data.result : null;
            new ItemGenerator(getActivity()).init(rootView, parentCount, layoutType, parentHeading, childItems, results);
        }
    }
}
