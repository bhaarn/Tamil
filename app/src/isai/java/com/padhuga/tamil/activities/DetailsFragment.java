package com.padhuga.tamil.activities;

import android.content.Context;
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

    public static DetailsFragment newInstance(int position, int groupPosition, int childPosition) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putInt(Constants.ARG_SECTION_POSITION, position);
        args.putInt(Constants.ARG_PARENT_POSITION, groupPosition);
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
        int groupPosition = getArguments().getInt(Constants.ARG_PARENT_POSITION);
        int childPosition = getArguments().getInt(Constants.ARG_CHILD_POSITION);
        Data data = ((BaseActivity) getActivity()).parentModel.data.type.get(position).type.get(groupPosition).type.get(childPosition);
        BaseTextView childTitle = rootView.findViewById(R.id.child_title);
        BaseTextView childSoothiram = rootView.findViewById(R.id.child_soothiram);
        BaseTextView childContent = rootView.findViewById(R.id.child_content);
        BaseTextView childExample = rootView.findViewById(R.id.child_example);

        childTitle.setText(data.title);
        childSoothiram.setText(data.soothiram);
        childContent.setText(data.desc);
        childExample.setText(data.example);

        if (!childSoothiram.getText().equals("")) {
            childSoothiram.setVisibility(View.VISIBLE);
        }
        setGrandChildData(data, rootView);
    }

    private void setGrandChildData(Data data, ViewGroup rootView) {
        final ViewGroup nullParent = null;
        for (int grandChildCount = 0; grandChildCount < data.type.size(); grandChildCount++) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.content_grandchild, nullParent);

            BaseTextView grandChildTitle = view.findViewById(R.id.grand_child_title);
            BaseTextView grandChildSoothiram = view.findViewById(R.id.grand_child_soothiram);
            BaseTextView grandChildContent = view.findViewById(R.id.grand_child_content);
            BaseTextView grandChildExample = view.findViewById(R.id.grand_child_example);

            grandChildTitle.setText(data.type.get(grandChildCount).title);
            grandChildSoothiram.setText(data.type.get(grandChildCount).soothiram);
            grandChildContent.setText(data.type.get(grandChildCount).desc);
            grandChildExample.setText(data.type.get(grandChildCount).example);

            ViewGroup viewGroup = rootView.findViewById(R.id.overall_parent);
            viewGroup.addView(view, -1, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        }
    }
}
