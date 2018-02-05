package com.padhuga.tamil.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.padhuga.tamil.R;
import com.padhuga.tamil.models.Data;
import com.padhuga.tamil.utils.BaseTextView;
import com.padhuga.tamil.utils.Constants;

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
        int position = getArguments().getInt(Constants.ARG_SECTION_POSITION);
        int childPosition = getArguments().getInt(Constants.ARG_CHILD_POSITION);
        Data data = ((BaseActivity) getActivity()).parentModel.getData().getType().get(position).getType().get(childPosition);

        BaseTextView childTitle = rootView.findViewById(R.id.child_title);
        BaseTextView childSoothiram = rootView.findViewById(R.id.child_soothiram);
        BaseTextView childContent = rootView.findViewById(R.id.child_content);
        BaseTextView childExample = rootView.findViewById(R.id.child_example);

        childTitle.setText(data.getTitle());
        childSoothiram.setText(data.getSoothiram());
        childContent.setText(data.getDesc());
        childExample.setText(data.getExample());

        if (!childSoothiram.getText().equals("")) {
            childSoothiram.setVisibility(View.VISIBLE);
        }
    }
}
