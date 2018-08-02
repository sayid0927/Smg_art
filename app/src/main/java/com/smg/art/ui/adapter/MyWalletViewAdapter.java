package com.smg.art.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.smg.art.ui.personalcenter.fragemnt.BalanceFragment;

/**
 * Created by Mervin on 2018/8/2 0002.
 */

public class MyWalletViewAdapter extends FragmentPagerAdapter {
    String[] titles;

    public MyWalletViewAdapter(FragmentManager fm, String[] title) {
        super(fm);
        this.titles = title;
    }

    @Override
    public Fragment getItem(int position) {
        BalanceFragment balanceFragment = new BalanceFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", position);
        balanceFragment.setArguments(bundle);
        return balanceFragment;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
