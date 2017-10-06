package com.gnyblecraft.marcul.ideasproject.Registry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gnyblecraft.marcul.ideasproject.R;

/**
 * Created by AndroidDeveloper on 04.10.17.
 */

public class FacebookActivity extends Activity{

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        FacebookSdk.setApplicationId(FacebookSdk.APPLICATION_ID_PROPERTY);

        FacebookSdk.sdkInitialize(this.getApplicationContext());
        AppEventsLogger.activateApp(this);


        setContentView(R.layout.activity_login);


        callbackManager = CallbackManager.Factory.create();
        loginButton = findViewById(R.id.FBLogInButton);
        loginButton.setReadPermissions("public_profile", "email", "user_friends");

        //LoginManager.getInstance().registerCallback(

                loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.e("FACEBOOK","ACTIVE onSuccess");

                Log.e("Success", "Login");
                Log.e("FB getApplicationId: ", loginResult.getAccessToken().getApplicationId());
                Log.e("FB getToken: ", loginResult.getAccessToken().getToken());
                Log.e("FB getUserId: ", loginResult.getAccessToken().getUserId());
            }

            @Override
            public void onCancel() {
                Log.e("FACEBOOK","ACTIVE onCancel");

                Toast.makeText(FacebookActivity.this, "Login Cancel", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(FacebookActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                Log.e("FB Error:", error.getMessage());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }



}
