package com.gnyblecraft.marcul.ideasproject.userProfile.editProfile;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableField;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;

import com.gnyblecraft.marcul.ideasproject.ExploreActivity;
import com.gnyblecraft.marcul.ideasproject.R;
import com.gnyblecraft.marcul.ideasproject.base.BaseViewModel;
import com.gnyblecraft.marcul.ideasproject.databinding.ActivityEditProfileBinding;
import com.gnyblecraft.marcul.ideasproject.userProfile.entity.DatePicker;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by lenovo on 18.10.2017.
 */

public class EditProfileViewModel implements BaseViewModel {

    public final ObservableField<String> name = new ObservableField<>(ExploreActivity.preferences.getString(ExploreActivity.USER_NAME,""));
    public ObservableField<String> surname = new ObservableField<>(ExploreActivity.preferences.getString(ExploreActivity.USER_SURNAME,""));
    public ObservableField<String> country = new ObservableField<>("");
    public ObservableField<String> city = new ObservableField<>("");
    public ObservableField<String> address = new ObservableField<>("");
    public ObservableField<String> firstEmail = new ObservableField<>(ExploreActivity.preferences.getString(ExploreActivity.USER_EMAIL,""));
    public ObservableField<String> phone = new ObservableField<>("");
    public ObservableField<String> secondEmail = new ObservableField<>("");
    public ObservableField<String> birthday = new ObservableField<>("");
    public ObservableField<String> otherInformation = new ObservableField<>("");


    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    Activity activity;
    ActivityEditProfileBinding binding;
    public EditProfileViewModel(EditProfileActivity editProfileActivity, ActivityEditProfileBinding binding) {
        this.activity = editProfileActivity;
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

    private boolean validateAll(){
        int errorNum=0;
        if(!validateName())
            errorNum++;
        if(!validateSurname())
            errorNum++;
        if(!validateFirstEmail())
            errorNum++;
        if(!validateSecondEmail())
            errorNum++;

        if(errorNum==0)
            return true;
        else
            return false;
    }

    private boolean validateName() {
        if (name.get().trim().isEmpty()) {
            binding.editProfileName.setError(activity.getString(R.string.error_msg_name));
            requestFocus(binding.editProfileName);
            return false;
        } else {
            binding.textInputEditProfileName.setErrorEnabled(false);
        }

        return true;
    }

    private boolean validateSurname() {
        if (surname.get().trim().isEmpty()) {
            binding.editProfileSurname.setError(activity.getString(R.string.error_msg_name));
            requestFocus(binding.editProfileSurname);
            return false;
        } else {
            binding.textInputEditProfileSurname.setErrorEnabled(false);
        }

        return true;
    }
    private boolean validateFirstEmail() {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(firstEmail.get().trim());

        if(matcher.matches() || firstEmail.get().isEmpty()){
            return true;
        } else {
            binding.editFirstEmailProfile.setError(activity.getString(R.string.error_msg_email));
            requestFocus(binding.editFirstEmailProfile);
            return false;
        }
    }

    private boolean validateSecondEmail() {
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(secondEmail.get().trim());

        if(matcher.matches() || secondEmail.get().isEmpty()){
            return true;
        } else {
            binding.editSecondEmailProfile.setError(activity.getString(R.string.error_msg_email));
            requestFocus(binding.editSecondEmailProfile);
            return false;
        }
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
                activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }


    public void onEditPhotoClick(){





    }


    public void onConfirmClick(){

        if(validateAll()==true) {
            binding.imageConfirmChanges.setAlpha(1.0f);
            /*Log.e("Confirm changes:", name.get());
            Log.e("Confirm changes:", surname.get());
            Log.e("Confirm changes:", address.get());
            Log.e("Confirm changes:", phone.get());
            Log.e("Confirm changes:", firstEmail.get());
            Log.e("Confirm changes:", secondEmail.get());
            Log.e("Confirm changes:", birthday.get());
        */}

    }

    public void onCrossEditProfileClick(){
        binding.imageCrossEditProfile.setAlpha(1.0f);
        Intent intent = new Intent(activity, ExploreActivity.class);
        intent.putExtra("show profile","go");
        activity.startActivity(intent);
        activity.finish();
        activity.overridePendingTransition(R.anim.alpha_up,R.anim.bottom_top_anim);
    }

    public void onBdayClick(){
        binding.editBirthdayProfile1.setVisibility(View.VISIBLE);
       DatePicker dateDialog = new DatePicker();
        dateDialog.show(activity.getFragmentManager(),"datePicker");

    }
    public void onChangePassClick(){
        Intent intent = new Intent(activity,ChangePasswordActivity.class);
        activity.startActivity(intent);
        //анимашка снизу вверх
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.edit_profileName:
                    validateName();
                    break;
                case R.id.edit_firstEmailProfile:
                    validateFirstEmail();
                    break;
            }
        }
    }

}
