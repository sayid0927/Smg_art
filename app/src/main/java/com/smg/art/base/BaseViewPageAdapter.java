package com.smg.art.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.smg.art.ui.personalcenter.fragemnt.AuctionOrderFragment;


/**
 * Created by Mervin on 2018/8/1 0001.
 */

public class BaseViewPageAdapter extends FragmentPagerAdapter {
    String[] titles;

    public BaseViewPageAdapter(FragmentManager fm, String[] title) {
        super(fm);
        this.titles = title;
    }

    @Override
    public Fragment getItem(int position) {
        AuctionOrderFragment auctionOrderFragment = new AuctionOrderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", position);
        auctionOrderFragment.setArguments(bundle);
        return auctionOrderFragment;
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
