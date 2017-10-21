package com.gnyblecraft.marcul.ideasproject.userProfile.editProfile;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.gnyblecraft.marcul.ideasproject.R;
import com.gnyblecraft.marcul.ideasproject.databinding.ActivityEditProfileBinding;

/**
 * Created by lenovo on 17.10.2017.
 */

public class EditProfileActivity extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        ActivityEditProfileBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_edit_profile);
        EditProfileViewModel viewModel = new EditProfileViewModel(this,binding);
        binding.setViewModel(viewModel);


        super.onCreate(savedInstanceState);
    }

}
