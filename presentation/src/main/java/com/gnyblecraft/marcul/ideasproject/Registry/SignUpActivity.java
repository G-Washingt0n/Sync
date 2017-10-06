package com.gnyblecraft.marcul.ideasproject.Registry;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.gnyblecraft.marcul.ideasproject.ExploreActivity;
import com.gnyblecraft.marcul.ideasproject.R;
import com.gnyblecraft.marcul.ideasproject.base.BaseActivity;
import com.gnyblecraft.marcul.ideasproject.base.BaseViewModel;
import com.gnyblecraft.marcul.ideasproject.databinding.ActivitySignupBinding;
import com.strongloop.android.loopback.RestAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by nyblecraft on 22.09.17.
 */

public class SignUpActivity extends BaseActivity{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        ActivitySignupBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_signup);

        SignUpViewModel viewModel = new SignUpViewModel(this,binding);
        this.viewModel = viewModel;

        binding.setViewModel(viewModel);

        super.onCreate(savedInstanceState);

    }




    }


