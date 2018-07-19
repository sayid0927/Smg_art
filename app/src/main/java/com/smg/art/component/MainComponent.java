package com.smg.art.component;


import com.smg.art.ui.activity.MainActivity;

import dagger.Component;


@Component(dependencies = AppComponent.class)
public interface MainComponent {

    MainActivity inject(MainActivity activity);

}