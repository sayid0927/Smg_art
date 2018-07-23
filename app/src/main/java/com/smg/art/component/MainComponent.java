package com.smg.art.component;



import com.smg.art.ui.activity.MainActivity;
import com.smg.art.ui.fragment.HomeFragment;

import dagger.Component;


@Component(dependencies = AppComponent.class)
public interface MainComponent {

    MainActivity inject(MainActivity activity);

    HomeFragment inject(HomeFragment homeFragment);

}