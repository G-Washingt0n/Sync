package com.gnyblecraft.marcul.ideasproject.SideNavigationMenu;

import android.app.Activity;

import com.gnyblecraft.marcul.ideasproject.base.BaseViewModel;
import com.gnyblecraft.marcul.ideasproject.databinding.ActivityAboutusBinding;

/**
 * Created by lenovo on 06.10.2017.
 */

public class AboutUsViewModel implements BaseViewModel {

    Activity activity;
    ActivityAboutusBinding binding;

    public AboutUsViewModel(Activity activity, ActivityAboutusBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    @Override
    public void init() {

    }

    @Override
    public void release() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }
}
