package com.padhuga.tamil.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.padhuga.tamil.activities.DetailsFragment;
import com.padhuga.tamil.models.ParentModel;

public class SectionDetailAdapter extends FragmentPagerAdapter {

    private final ParentModel parentModel;
    private final int position;

    public SectionDetailAdapter(FragmentManager fm, ParentModel parentModel, int position) {
        super(fm);
        this.parentModel = parentModel;
        this.position = position;
    }

    @Override
    public Fragment getItem(int position) {
        return DetailsFragment.newInstance(this.position, position);
    }

    @Override
    public int getCount() {
            return parentModel.data.type.get(position).type.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.parentModel.data.type.get(position).title;
    }
}
