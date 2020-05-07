package com.aspsine.fragmentnavigator.demo.ui.fragment;


import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aspsine.fragmentnavigator.FragmentNavigator;
import com.aspsine.fragmentnavigator.demo.R;
import com.aspsine.fragmentnavigator.demo.ui.widget.TabLayout;
import com.aspsine.fragmentnavigator.demo.ui.adapter.ChildFragmentAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactsFragment extends Fragment {

    public static final String TAG = ContactsFragment.class.getSimpleName();

    private FragmentNavigator mNavigator;

    private TabLayout tabLayout;

    public ContactsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNavigator = new FragmentNavigator(getChildFragmentManager(), new ChildFragmentAdapter(), R.id.childContainer);
        mNavigator.setDefaultPosition(0);
        mNavigator.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contacts, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        tabLayout.setOnTabItemClickListener(new TabLayout.OnTabItemClickListener() {
            @Override
            public void onTabItemClick(int position, View view) {
                setCurrentTab(position);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setCurrentTab(mNavigator.getCurrentPosition());
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mNavigator.onSaveInstanceState(outState);
    }

    public void setCurrentTab(int position) {
        mNavigator.showFragment(position);
        tabLayout.select(position);
    }


    public static Fragment newInstance(int position) {
        Fragment fragment = new ContactsFragment();
        return fragment;
    }
}
