package com.gnyblecraft.marcul.ideasproject.Registry;

import android.app.Activity;
import android.content.Intent;
import android.databinding.ObservableField;
import android.util.Log;

import com.gnyblecraft.marcul.domain.entity.CreateProfileModel;
import com.gnyblecraft.marcul.domain.CreateProfileUseCase;
import com.gnyblecraft.marcul.domain.entity.ResponseCreateProfileModel;
import com.gnyblecraft.marcul.ideasproject.ExploreActivity;
import com.gnyblecraft.marcul.ideasproject.base.BaseViewModel;
import com.gnyblecraft.marcul.ideasproject.databinding.ActivitySignupBinding;

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
                ExploreActivity.setPreferences(ExploreActivity.USER_NAME,responseCreateProfileModel.getName());

                //взять инфу от сервера и послать в профиль

                Log.e("onNext: ", "All OK");
                goToLogIn(responseCreateProfileModel.getEmail(),password.get());
            }

            @Override
            public void onError(@NonNull Throwable e) {

                Log.e("OTVET Register", "error = ", e);
            }

            @Override
            public void onComplete() {

            }
        });

    }

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
