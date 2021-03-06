package com.padhuga.tamil.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.padhuga.tamil.R;
import com.padhuga.tamil.adapters.SectionDetailAdapter;
import com.padhuga.tamil.utils.Constants;

public class DetailsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        int position = getIntent().getIntExtra(Constants.ARG_SECTION_POSITION, -1);
        int groupPosition = getIntent().getIntExtra(Constants.ARG_PARENT_POSITION, -1);
        int childPosition = getIntent().getIntExtra(Constants.ARG_CHILD_POSITION, -1);
        Initialize(position, groupPosition, childPosition);
    }

    private void Initialize(int position, int groupPosition, int childPosition) {
        initializeAds(this);
        SectionDetailAdapter mSectionDetailAdapter = new SectionDetailAdapter(getSupportFragmentManager(), parentModel, position, groupPosition);
        ViewPager mViewPager = findViewById(R.id.container);
        mViewPager.setAdapter(mSectionDetailAdapter);
        mViewPager.setCurrentItem(childPosition);
    }
}
