package com.padhuga.tamil.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.padhuga.tamil.activities.MainFragment;
import com.padhuga.tamil.models.ParentModel;

public class SectionPagerAdapter extends FragmentPagerAdapter {

    private final ParentModel parentModel;

    public SectionPagerAdapter(FragmentManager fm, ParentModel parentModel) {
        super(fm);
        this.parentModel = parentModel;
    }

    @Override
    public Fragment getItem(int position) {
        return MainFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return parentModel.getData().getType().size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return parentModel.getData().getType().get(position).getTitle();
    }
}
