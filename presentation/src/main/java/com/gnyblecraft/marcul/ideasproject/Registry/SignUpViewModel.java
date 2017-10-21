package com.gnyblecraft.marcul.ideasproject.Registry;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;
import android.widget.Toast;

import com.gnyblecraft.marcul.domain.CreateProfileUseCase;
import com.gnyblecraft.marcul.domain.entity.CreateProfileModel;
import com.gnyblecraft.marcul.domain.entity.ResponseCreateProfileModel;
import com.gnyblecraft.marcul.ideasproject.ExploreActivity;
import com.gnyblecraft.marcul.ideasproject.base.BaseViewModel;
import com.gnyblecraft.marcul.ideasproject.databinding.ActivitySignupBinding;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by AndroidDeveloper on 28.09.17.
 */

public class SignUpViewModel implements BaseViewModel {

    Activity activity;
    ActivitySignupBinding binding;
    public ObservableField<String> name = new ObservableField<>();
    public ObservableField<String> surname = new ObservableField<>();
    public ObservableField<String> email = new ObservableField<>();
    public ObservableField<String> password = new ObservableField<>();
    public ObservableField<String> phone = new ObservableField<>();


    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                    "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public SignUpViewModel(Activity activity, ActivitySignupBinding binding) {
        this.activity = activity;
        this.binding = binding;
    }

    public CreateProfileUseCase createProfileUseCase = new CreateProfileUseCase();

    @Override
    public void init() {

    }

    @Override
    public void release() {

    }

    @Override
    public void resume() {


    }


    public void onSignUpClick(){

        if(validate(name.get())){

        }
        CreateProfileModel profileModel = new CreateProfileModel();
        profileModel.setName(name.get());
        profileModel.setSurname(surname.get());
        profileModel.setEmail(email.get());
        profileModel.setPassword(password.get());
        if(phone.get()!=null)
            profileModel.setPhone(phone.get());
        else
            profileModel.setPhone("");

        Log.e("Check phone status: ", profileModel.getPhone());

        createProfileUseCase.execute(profileModel, new DisposableObserver<ResponseCreateProfileModel>() {

            @Override
            public void onNext(@NonNull ResponseCreateProfileModel responseCreateProfileModel) {
                Log.e("onNext registry: ", "All OK!!");
                goToLogIn(responseCreateProfileModel.getEmail(),password.get());
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.e("OTVET Registry", "error = ", e);
            }

            @Override
            public void onComplete() {

            }
        });

    }

    private boolean validate(String name) {
        if(name==null){
            Toast.makeText(activity, "Empty name field!", Toast.LENGTH_LONG).show();
            return false;
        }
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile("[A-Za-z]+");
        matcher = pattern.matcher(name);


        if(matcher.matches()){
            return validateSurname(surname.get());
        } else{
            Toast.makeText(activity, "Wrong name form!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean validateSurname(String surname) {
        if(surname==null){
            Toast.makeText(activity, "Empty surname field!", Toast.LENGTH_LONG).show();
            return false;
        }
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile("[A-Za-z]+");
        matcher = pattern.matcher(surname);
        if(matcher.matches()){
            return validateEmail(email.get());
        } else {
            Toast.makeText(activity, "Wrong surname form!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean validateEmail(String email) {
        if(email==null){
            Toast.makeText(activity, "Empty email field!", Toast.LENGTH_LONG).show();
            return false;
        }
        Pattern pattern;
        Matcher matcher;
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        if(matcher.matches()){
            return validatePhone(phone.get());
        } else {
            Toast.makeText(activity, "Wrong email format!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    private boolean validatePhone(String phone) {
            return validatePassword(password.get());
    }

    private boolean validatePassword(String password) {
        if(password!=null){
            return true;
        } else {
            Toast.makeText(activity, "Empty password!", Toast.LENGTH_LONG).show();
            return false;
        }
    }

  /*  public void onFBSignUpClick(){
        Toast.makeText(activity, "For FB activities please use Log in screen instead", Toast.LENGTH_LONG).show();
    }*/

    public void onCrossClick(){
        binding.imageCrossSignup.setAlpha(1.0f);
        Intent intent = new Intent(activity,ExploreActivity.class);
        activity.startActivity(intent);
    }

    public void onLogInClick(){
        goToLogIn();
    }
    @Override
    public void pause() {
        try{
            createProfileUseCase.dispose();
        } catch (Exception e){
            Log.e("Error: ", e.toString());
        }

    }

    public void goToLogIn(){
        Intent goToLogIn = new Intent(activity, LogInActivity.class);
        activity.startActivity(goToLogIn);
    }
    public void goToLogIn(String email, String password){
        Intent goToLogIn = new Intent(activity, LogInActivity.class);
        goToLogIn.putExtra("email", email);
        goToLogIn.putExtra("password",password);
        activity.startActivity(goToLogIn);
    }
}
