package com.gnyblecraft.marcul.ideasproject.Registry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.gnyblecraft.marcul.domain.CreateFBProfileUseCase;
import com.gnyblecraft.marcul.domain.entity.FBModel;
import com.gnyblecraft.marcul.domain.entity.ResponseFBModel;
import com.gnyblecraft.marcul.ideasproject.ExploreActivity;
import com.gnyblecraft.marcul.ideasproject.R;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.ObjectCallback;

import java.util.Arrays;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by AndroidDeveloper on 04.10.17.
 */

public class FacebookActivity extends Activity{

    private String hostname = "https://save-ideas.com/api";

    private AccessTokenTracker accessTokenTracker;
    private AccessToken accessToken;
    private Button loginButton;
    private CallbackManager callbackManager;
    public CreateFBProfileUseCase createFBProfileUseCase = new CreateFBProfileUseCase();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        LoginManager.getInstance().getLoginBehavior();
        loginWithReadPermissions();

        callbackManager = new CallbackManager() {
            @Override
            public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
                try {
                    FacebookActivity.super.onActivityResult(requestCode, resultCode, data);
                    callbackManager.onActivityResult(requestCode, resultCode, data);
                    return true;
                } catch (Exception e){
                    return false;
                }
            }
        };

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {
                Log.e(getClass().getName(), "error = " + error);
            }
        });

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                accessToken = currentAccessToken;
                if (accessToken == null) {
                    Intent intent = new Intent(FacebookActivity.this, LogInActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.top_bottom_anim,R.anim.alpha_down);

                } else {


                    FBModel model = new FBModel();
                    model.setAccessToken(accessToken.getToken());

                    Log.e("Проверка Token где надо",model.getAccessToken());
                    ExploreActivity.setPreferences(ExploreActivity.TOKEN_NAME,accessToken.getToken()); //временно, потом брать норм токен

                    createFBProfileUseCase.execute(model, new DisposableObserver<ResponseFBModel>() { // после нажатия на кнопку срабатывает тут
                        @Override
                        public void onNext(@NonNull ResponseFBModel responseCreateProfileModel) {

                            getFBuser(responseCreateProfileModel.getUserId());
                            //взять информацию и послать в профиль
                            Log.e("onNext: ", "All OK!!!!!!!!!");
                        }

                        @Override
                        public void onError(@NonNull Throwable e) {
                            Log.e("onError: ", "Bad!!!!!!!!!! " + e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });


                    Intent intent = new Intent(FacebookActivity.this, ExploreActivity.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.top_bottom_anim,R.anim.alpha_down);

                }

            }
        };

        Log.e("tracker", String.valueOf(accessTokenTracker.isTracking()));
        if (AccessToken.getCurrentAccessToken() != null) { //при переходе на экран логина проверяется токен фб, он в accessToken
            accessToken = AccessToken.getCurrentAccessToken();


            Log.e("Check FB status: ", accessToken.toString());
        }



        super.onCreate(savedInstanceState);
    }

    private void getFBuser(String userId) {

        RestAdapter adapter = new RestAdapter(getApplicationContext(),hostname);
        LogInViewModel.UserRepository userRepository = adapter.createRepository(LogInViewModel.UserRepository.class);

        userRepository.findById(userId, new ObjectCallback<LogInViewModel.User>() {
            @Override
            public void onSuccess(LogInViewModel.User object) {
                Log.e("FB result name: ",object.getName());
                Log.e("FB result name: ",object.getSurname());

                Log.e("FB result name: ",object.getEmail());

                Log.e("FB result name: ",object.getPictureUrl());

                Log.e("FB result name: ",object.getPhone());

            }

            @Override
            public void onError(Throwable t) {
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            accessTokenTracker.stopTracking();
        } catch (Exception e) {
            Log.e("AccesstokenTracker",e.toString());
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(FacebookActivity.this, ExploreActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.top_bottom_anim,R.anim.alpha_down);
    }

    private void loginWithReadPermissions() {
        LoginManager.getInstance()
                .logInWithReadPermissions(this, Arrays.asList("email"));
    }





}
