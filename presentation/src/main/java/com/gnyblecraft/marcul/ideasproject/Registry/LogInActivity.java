package com.gnyblecraft.marcul.ideasproject.Registry;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.gnyblecraft.marcul.ideasproject.R;
import com.gnyblecraft.marcul.ideasproject.databinding.ActivityLoginBinding;


/**
 * Created by nyblecraft on 22.09.17.
 */

public class LogInActivity extends Activity {

    private AccessTokenTracker accessTokenTracker;
    private Button loginButton;
    private AccessToken accessToken;
    private CallbackManager callbackManager;

    public LogInActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
       // FacebookSdk.setApplicationId(FacebookSdk.APPLICATION_ID_PROPERTY);
        //FacebookSdk.sdkInitialize(getApplicationContext());
        //AppEventsLogger.activateApp(this);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_login);








        try{
            Intent intent = getIntent();
            String registeredEmail = intent.getStringExtra("email");
            String registeredPassword = intent.getStringExtra("password");
            Log.e("registeredEmail: ", registeredEmail);
            Log.e("registeredPassword: ", registeredPassword);
            LogInViewModel viewModel = new LogInViewModel(this,binding,registeredEmail,registeredPassword);
            binding.setViewModel(viewModel);
        } catch (Exception e){
            LogInViewModel viewModel = new LogInViewModel(this,binding);
            binding.setViewModel(viewModel);
            Log.e("No income intent: ", e.toString());



           /* callbackManager = new CallbackManager() {
                @Override
                public boolean onActivityResult(int requestCode, int resultCode, Intent data) {
                    try {
                        LogInActivity.super.onActivityResult(requestCode, resultCode, data);
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
                        Intent intent = new Intent(LogInActivity.this, LogInActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Intent intent = new Intent(LogInActivity.this, ExploreActivity.class);
                        startActivity(intent);
                        finish();
                    }

                }
            };
            Log.e("tracker", String.valueOf(accessTokenTracker.isTracking()));
            if (AccessToken.getCurrentAccessToken() != null) {
                accessToken = AccessToken.getCurrentAccessToken();
            }

            loginButton = binding.FBLogInButton;
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoginManager.getInstance().getLoginBehavior();
                    loginWithReadPermissions();
                }
            });

*/





        }




        super.onCreate(savedInstanceState);
    }

  /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    private void loginWithReadPermissions() {
        LoginManager.getInstance().logInWithReadPermissions(
                this, Arrays.asList("email"));
    }*/
}

