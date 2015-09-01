package org.cathassist.daily.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.cathassist.daily.R;
import org.cathassist.daily.provider.EnumManager;
import org.cathassist.daily.provider.MainActivityFragmentPagerAdapter;
import org.cathassist.daily.util.TimeFormatter;

import java.util.ArrayList;
import java.util.Calendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabFragment extends Fragment {

    private MainActivity mainActivity;
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private MainActivityFragmentPagerAdapter pagerAdapter;

    public TabFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        mainActivity = (MainActivity) activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tab, container, false);
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        setupToolbar();
        viewPager = (ViewPager) view.findViewById(R.id.tab_view_pager);
        setupViewPager();
        tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        setupTab();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mainActivity.setupNavigationDrawer(toolbar);
    }

    private void setupToolbar() {
        mainActivity.setSupportActionBar(toolbar);
    }

    private void setupTab() {
        tabLayout.setupWithViewPager(viewPager);
    }


    private void setupViewPager() {
        Calendar c = Calendar.getInstance();
        String dateString = TimeFormatter.formatDateYYYYMMDD(c
                .getTimeInMillis());
        pagerAdapter = new MainActivityFragmentPagerAdapter(getActivity(), getChildFragmentManager(), fragments(dateString));
        viewPager.setAdapter(pagerAdapter);
    }

    public ArrayList<Fragment> fragments(String date) {
        ArrayList<Fragment> fragmentList = new ArrayList<Fragment>();
        for (int i = 0; i < 7; i++) {
            if (i == 0) {
                fragmentList.add(MainFragment.newInstance(date));
            } else {
                PrayFragment fragment;
                switch (i) {
                    case 1:
                        fragment = PrayFragment.newInstance(date, 0);
                        break;
                    case 2:
                        fragment = PrayFragment.newInstance(date, 1);
                        break;
                    case 3:
                        fragment = PrayFragment.newInstance(date, 4);
                        break;
                    case 4:
                        fragment = PrayFragment.newInstance(date, 2);
                        break;
                    case 5:
                        fragment = PrayFragment.newInstance(date, 3);
                        break;
                    case 6:
                        fragment = PrayFragment.newInstance(date, 5);
                        break;
                    default:
                        fragment = PrayFragment.newInstance(date, 0);
                        break;
                }
                fragmentList.add(fragment);
            }
        }
        return fragmentList;
    }


    public void setViewPagerCurrentItem(int page) {
        viewPager.setCurrentItem(page);
    }

    public void setDate(String date) {
        pagerAdapter.setFragmentDate(date);
//        pagerAdapter.setFragments(fragments(date));
    }

    public void setTextSize(EnumManager.FontSize fontSize){
        pagerAdapter.setTextSize(fontSize);
    }
}
