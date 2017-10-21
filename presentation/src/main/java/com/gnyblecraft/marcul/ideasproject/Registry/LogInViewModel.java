package com.gnyblecraft.marcul.ideasproject.Registry;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.Toast;

import com.gnyblecraft.marcul.ideasproject.ExploreActivity;
import com.gnyblecraft.marcul.ideasproject.R;
import com.gnyblecraft.marcul.ideasproject.userProfile.entity.ProfileModel;
import com.gnyblecraft.marcul.ideasproject.base.BaseViewModel;
import com.gnyblecraft.marcul.ideasproject.databinding.ActivityLoginBinding;
import com.strongloop.android.loopback.AccessToken;
import com.strongloop.android.loopback.RestAdapter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by AndroidDeveloper on 02.10.17.
 */

public class LogInViewModel implements BaseViewModel {

    Activity activity;
    ActivityLoginBinding binding;


    //public enum STATE {PROGRESS, DATA}

    public ObservableField<String> loginEmail = new ObservableField<>();
    public ObservableField<String> loginPassword = new ObservableField<>();

    //ProgressBar progressBar = (ProgressBar) binding.progressbarLogin;

    private Pattern pattern;
    private Matcher matcher;
    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";


    String usermail;
    String password;
    private String hostname = "https://save-ideas.com/api";


    public LogInViewModel(Activity activity, ActivityLoginBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    public LogInViewModel(Activity activity, ActivityLoginBinding binding, String registeredEmail, String registeredPassword) {
        this.activity = activity;
        this.binding = binding;
        this.loginEmail.set(registeredEmail);
        this.loginPassword.set(registeredPassword);
    }

    public static class User extends com.strongloop.android.loopback.User {
        private String name;
        private String surname;
        private String city;
        private String country;
        private String address;
        private String phone;
        private String email;
        private String secondEmail;
        private long birthDate;
        private String details;
        private String pictureUrl;

        public String getPictureUrl() {
            return pictureUrl;
        }

        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }


        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getSecondEmail() {
            return secondEmail;
        }

        public void setSecondEmail(String secondEmail) {
            this.secondEmail = secondEmail;
        }

        public long getBirthDate() {
            return birthDate;
        }

        public void setBirthDate(long birthDate) {
            this.birthDate = birthDate;
        }

        public String getDetails() {
            return details;
        }

        public void setDetails(String details) {
            this.details = details;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        @Override
        public String getEmail() {
            return email;
        }

        @Override
        public void setEmail(String email) {
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }
    }

    public static class UserRepository
            extends com.strongloop.android.loopback.UserRepository<User> {
        public interface LoginCallback
                extends com.strongloop.android.loopback.UserRepository.LoginCallback<User> {
        }

        public UserRepository() {
            super("user", null, User.class);
        }
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

    public void onLogInClick(){
        int critery=0;


        if(loginEmail.get()!=null && validateMail(loginEmail.get())){
           critery++;
        } else{
            Toast.makeText(activity, "Wrong email adress!!!", Toast.LENGTH_LONG).show();
        }

        if(loginPassword.get()!=null){
            critery++;
        }
        else{
            Toast.makeText(activity, "Empty password field!", Toast.LENGTH_LONG).show();
        }



        if(critery==2){
            final RestAdapter restAdapter = new RestAdapter(activity.getApplicationContext(), hostname);
            final UserRepository userRepo = restAdapter.createRepository(UserRepository.class);
            logInAction(userRepo);

            //state.set(STATE.DATA); с прогрессбаром проблема какая-то :/
            //progressBar.setVisibility(View.VISIBLE);
        }
    }

    private boolean validateMail(final String mail) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(mail);

        return matcher.matches();
    }

    public void goToSignUp(){
        Intent goToSignup = new Intent(activity, SignUpActivity.class);
        activity.startActivity(goToSignup);
        activity.finish();
        activity.overridePendingTransition(R.anim.top_bottom_anim,R.anim.alpha_down);

    }

    public void onCrossClick(){
        binding.imageCross.setAlpha(1.0f);
        Intent intent = new Intent(activity, ExploreActivity.class);
        activity.startActivity(intent);
        activity.finish();
        activity.overridePendingTransition(R.anim.alpha_up,R.anim.bottom_top_anim);

    }

    public void onForgotPassword(){
        Intent intent = new Intent(activity,ResetPasswordActivity.class);
        activity.startActivity(intent);
        activity.overridePendingTransition(R.anim.alpha_up,R.anim.bottom_top_anim);

    }


    private void logInAction(final UserRepository userRepo){

        usermail = loginEmail.get();
        password = loginPassword.get();

        Log.e("AAAAA", usermail + " : " + password);
        userRepo.loginUser(usermail, password, new UserRepository.LoginCallback() {
            @Override
            public void onSuccess(AccessToken token, User currentUser) {

                currentUser.setEmail(usermail);
                currentUser.setPassword(password);

                Log.e("TOKEN", /*token.getUserId() + ":" +*/ token.getId().toString()/*currentUser.toString()*/);
                ExploreActivity.setPreferences(ExploreActivity.USER_ID,token.getUserId().toString());
                ExploreActivity.setPreferences(ExploreActivity.TOKEN_NAME, token.getId().toString());
                ExploreActivity.setPreferences(ExploreActivity.USER_NAME,currentUser.getName());
                ExploreActivity.setPreferences(ExploreActivity.USER_SURNAME,currentUser.getSurname());
                ExploreActivity.setPreferences(ExploreActivity.USER_EMAIL,currentUser.getEmail());
                ExploreActivity.setPreferences(ExploreActivity.USER_PHOTO_URL,currentUser.getPictureUrl());
                ExploreActivity.setPreferences(ExploreActivity.USER_TYPE,"Registered user");


                ProfileModel profileModel = ProfileModel.getInstance(0);
                profileModel.setName(currentUser.getName());
                profileModel.setSurname(currentUser.getSurname());
                profileModel.setCity(currentUser.getCity());
                profileModel.setCountry(currentUser.getCountry());
                profileModel.setAddress(currentUser.getAddress());
                profileModel.setPhone(currentUser.getPhone());
                profileModel.setEmail(currentUser.getEmail());
                profileModel.setSecondEmail(currentUser.getSecondEmail());
                profileModel.setBirthDate(currentUser.getBirthDate());
                profileModel.setDetails(currentUser.getDetails());
                profileModel.setPictureUrl(currentUser.getPictureUrl());



                Intent goToExplore = new Intent(activity, ExploreActivity.class);
                activity.startActivity(goToExplore);
                activity.finish();
                activity.overridePendingTransition(R.anim.alpha_up,R.anim.bottom_top_anim);

            }

            @Override
            public void onError(Throwable t) {
                Toast.makeText(activity, "Error: Please check your input data!", Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void pause() {

    }
}
