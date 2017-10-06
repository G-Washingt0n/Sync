package com.gnyblecraft.marcul.ideasproject.Registry;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.Toast;

import com.gnyblecraft.marcul.ideasproject.ExploreActivity;
import com.gnyblecraft.marcul.ideasproject.base.BaseViewModel;
import com.gnyblecraft.marcul.ideasproject.databinding.ActivityLoginBinding;
import com.strongloop.android.loopback.AccessToken;
import com.strongloop.android.loopback.RestAdapter;

/**
 * Created by AndroidDeveloper on 02.10.17.
 */

public class LogInViewModel implements BaseViewModel {

    Activity activity;
    ActivityLoginBinding binding;


    public enum STATE {PROGRESS, DATA}

    public ObservableField<String> loginEmail = new ObservableField<>();
    public ObservableField<String> loginPassword = new ObservableField<>();
    public ObservableField<STATE> state = new ObservableField<>(STATE.DATA);

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

        final RestAdapter restAdapter = new RestAdapter(activity.getApplicationContext(), hostname);
        final UserRepository userRepo = restAdapter.createRepository(UserRepository.class);

        state.set(STATE.PROGRESS);
        logInAction(userRepo);
        state.set(STATE.DATA);
    }

    public void onFBLogInClick(){
        int request = 0;
        /*Intent intent = new Intent(activity,FacebookActivity.class);
        activity.startActivityForResult(intent,request);
    */
        Toast.makeText(activity, "Not ready yet!", Toast.LENGTH_LONG).show();

    }

    public void goToSignUp(){
        Intent goToSignup = new Intent(activity, SignUpActivity.class);
        activity.startActivity(goToSignup);
        activity.finish();
    }

    public void onCrossClick(){
        binding.imageCross.setAlpha(1.0f);
        Intent intent = new Intent(activity, ExploreActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }

    public void onForgotPassword(){
        Intent intent = new Intent(activity,ResetPasswordActivity.class);
        activity.startActivity(intent);
    }


    private void logInAction(final UserRepository userRepo){

        for(int i=0;i<1000000000;i++){
            int a=i;
            a+=1;
        }
        usermail = loginEmail.get();
        password = loginPassword.get();
        //usermail = loginEmail.getText().toString();
        //password = loginPassword.getText().toString();
        Log.e("AAAAA", usermail + " : " + password);
        userRepo.loginUser(usermail, password, new UserRepository.LoginCallback() {
            @Override
            public void onSuccess(AccessToken token, User currentUser) {
                currentUser.setEmail(usermail);
                currentUser.setPassword(password);
                Log.e("TOKEN", /*token.getUserId() + ":" +*/ token.getId().toString()/*currentUser.toString()*/);
                ExploreActivity.setPreferences(ExploreActivity.TOKEN_NAME,token.getId().toString());
                ExploreActivity.setPreferences(ExploreActivity.USER_EMAIL,usermail);
                Intent goToExplore = new Intent(activity, ExploreActivity.class);
                activity.startActivity(goToExplore);
                activity.finish();
            }

            @Override
            public void onError(Throwable t) {
                Log.e("Chatome", "Login E", t);
            }
        });

    }

    @Override
    public void pause() {

    }
}
