package com.smg.art.component;



import com.smg.art.ui.activity.ClassifyActivity;
import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.activity.SearchActivity;
import com.smg.art.ui.fragment.HomeFragment;
import com.smg.art.ui.fragment.MyFragment;

import dagger.Component;


@Component(dependencies = AppComponent.class)
public interface MainComponent {

    MainActivity inject(MainActivity activity);

    HomeFragment inject(HomeFragment homeFragment);

    MyFragment inject(MyFragment myFragment);

    ClassifyActivity  inject (ClassifyActivity classifyActivity);

    SearchActivity inject(SearchActivity searchActivity);

}