package com.gnyblecraft.marcul.ideasproject.SideNavigationMenu;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gnyblecraft.marcul.ideasproject.R;
import com.gnyblecraft.marcul.ideasproject.base.BaseActivity;
import com.gnyblecraft.marcul.ideasproject.databinding.ActivityAboutusBinding;

/**
 * Created by lenovo on 06.10.2017.
 */

public class AboutUsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        ActivityAboutusBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_aboutus);
        AboutUsViewModel viewModel = new AboutUsViewModel(this,binding);
        this.viewModel = viewModel;

        binding.setViewModel(viewModel);

        super.onCreate(savedInstanceState);

    }
}
