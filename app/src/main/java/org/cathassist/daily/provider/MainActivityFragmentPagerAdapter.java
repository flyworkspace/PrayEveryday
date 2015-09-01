package org.cathassist.daily.provider;

import java.util.ArrayList;

import org.cathassist.daily.R;
import org.cathassist.daily.activity.MainFragment;
import org.cathassist.daily.activity.PrayFragment;


import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;

public class MainActivityFragmentPagerAdapter extends FragmentPagerAdapter {
    Activity context;
    private ArrayList<Fragment> fragments;
    private FragmentManager fm;

    public MainActivityFragmentPagerAdapter(Activity context,
                                            FragmentManager fragmentManager, ArrayList<Fragment> fragments) {
        super(fragmentManager);
        this.context = context;
        this.fm = fragmentManager;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public String getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.main);
            case 1:
                return context.getString(R.string.laudes);
            case 2:
                return context.getString(R.string.horamedia);
            case 3:
                return context.getString(R.string.matutinum);
            case 4:
                return context.getString(R.string.vesperae);
            case 5:
                return context.getString(R.string.completorium);
            case 6:
                return context.getString(R.string.mass);
            default:
                return "";
        }
    }

    public void setFragments(ArrayList<Fragment> fragments) {
        if (this.fragments != null) {
            FragmentTransaction ft = fm.beginTransaction();
            for (Fragment f : this.fragments) {
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            fm.executePendingTransactions();
        }
        this.fragments = fragments;
        notifyDataSetChanged();
    }

    public void setFragmentDate(String date) {
        for (Fragment fragment : fragments) {
            if (fragment instanceof MainFragment) {
                ((MainFragment)fragment).setDate(date);
            } else if (fragment instanceof PrayFragment) {
                ((PrayFragment)fragment).setDate(date);
            }
        }
    }

    public void setTextSize(EnumManager.FontSize fontSize) {
        for (Fragment fragment : fragments) {
            if (fragment instanceof PrayFragment) {
                ((PrayFragment)fragment).setTextSize(fontSize);
            }
        }
    }
}
