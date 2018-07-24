package com.smg.art.component;



import com.smg.art.ui.activity.ClassifyActivity;
import com.smg.art.ui.activity.GoodsDetailActivity;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.activity.SearchActivity;
import com.smg.art.ui.fragment.ClassifyChildFragment;
import com.smg.art.ui.fragment.HomeFragment;

import dagger.Component;


@Component(dependencies = AppComponent.class)
public interface MainComponent {

    MainActivity inject(MainActivity activity);

    HomeFragment inject(HomeFragment homeFragment);

    ClassifyActivity  inject (ClassifyActivity classifyActivity);

    SearchActivity inject(SearchActivity searchActivity);

    ClassifyChildFragment inject (ClassifyChildFragment  classifyChildFragment);

    GoodsDetailActivity inject (GoodsDetailActivity goodsDetailActivity);

}