package com.aspsine.fragmentnavigator.demo.ui.adapter;

import androidx.fragment.app.Fragment;

import com.aspsine.fragmentnavigator.FragmentNavigatorAdapter;
import com.aspsine.fragmentnavigator.demo.ui.fragment.MainFragment;

/**
 * Created by aspsine on 16/4/3.
 */
public class ChildFragmentAdapter implements FragmentNavigatorAdapter {

    public static final String[] TABS = {"Friends", "Groups", "Official"};

    @Override
    public Fragment onCreateFragment(int position) {
        return MainFragment.newInstance(TABS[position]);
    }

    @Override
    public String getTag(int position) {
        return MainFragment.TAG + TABS[position];
    }

    @Override
    public int getCount() {
        return TABS.length;
    }
}
