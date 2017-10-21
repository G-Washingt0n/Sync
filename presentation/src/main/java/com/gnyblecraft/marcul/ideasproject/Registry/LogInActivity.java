package com.gnyblecraft.marcul.ideasproject.Registry;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.gnyblecraft.marcul.domain.CreateFBProfileUseCase;
import com.gnyblecraft.marcul.domain.entity.FBModel;
import com.gnyblecraft.marcul.domain.entity.ResponseFBModel;
import com.gnyblecraft.marcul.ideasproject.ExploreActivity;
import com.gnyblecraft.marcul.ideasproject.R;
import com.gnyblecraft.marcul.ideasproject.base.WaitScreenActivity;
import com.gnyblecraft.marcul.ideasproject.databinding.ActivityLoginBinding;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.ObjectCallback;

import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;


/**
 * Created by nyblecraft on 22.09.17.
 */

public class LogInActivity extends FragmentActivity {

    private AccessTokenTracker accessTokenTracker;
    private Button loginButton;
    private AccessToken accessToken;
    private CallbackManager callbackManager;
    public CreateFBProfileUseCase createFBProfileUseCase = new CreateFBProfileUseCase();

    private String hostname = "https://save-ideas.com/api";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        FacebookSdk.setApplicationId(getString(R.string.facebook_app_id));
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        ActivityLoginBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_login);


        try{ //либо заполняются поля при переходе из регистрации, либо ошибка и простой переход на ViewModel
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



            callbackManager = new CallbackManager() {
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


                    ////////
                    GraphRequest request = GraphRequest.newMeRequest(
                            loginResult.getAccessToken(),
                            new GraphRequest.GraphJSONObjectCallback() {

                                @Override
                                public void onCompleted(JSONObject object, GraphResponse response) {
                                    Log.v("Main", response.toString());

                                  //  setProfileToView(object);
                                }
                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,email,gender, birthday");
                    request.setParameters(parameters);
                    request.executeAsync();

                    /////////


                    Intent intent = new Intent(LogInActivity.this, WaitScreenActivity.class);
                    startActivity(intent);


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
                        overridePendingTransition(R.anim.top_bottom_anim,R.anim.alpha_down);

                    } else {

                        FBModel model = new FBModel();
                        model.setAccessToken(accessToken.getToken());

                        Log.e("Проверка Token от FB",model.getAccessToken());

                        createFBProfileUseCase.execute(model, new DisposableObserver<ResponseFBModel>() { // после нажатия на кнопку срабатывает тут
                            @Override
                            public void onNext(@NonNull ResponseFBModel responseCreateProfileModel) {

                                ExploreActivity.setPreferences(ExploreActivity.USER_ID,responseCreateProfileModel.getUserId());
                                ExploreActivity.setPreferences(ExploreActivity.USER_TYPE,"FB user");
                                ExploreActivity.setPreferences(ExploreActivity.TOKEN_NAME, responseCreateProfileModel.getAccess_token());

                                getFBuser(responseCreateProfileModel.getUserId());  //взять информацию и послать в профиль

                                Log.e("onNext: ", "All OK!!!!!!!!!");

                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Log.e("onError: ", "Bad!!!!!!!!!! " + e.toString());
                            }

                            @Override
                            public void onComplete() {
                                Log.e("onComplete: ", "All OK!!!!!!!!!");
                            }
                        });

                    }

                }
            };

            Log.e("tracker", String.valueOf(accessTokenTracker.isTracking()));
            if (AccessToken.getCurrentAccessToken() != null) { //при переходе на экран логина проверяется токен фб, он в accessToken
                accessToken = AccessToken.getCurrentAccessToken();


                Log.e("Check FB status: ", accessToken.toString());
            }

            loginButton = binding.FBLogInButton;
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    LoginManager.getInstance().getLoginBehavior();
                    loginWithReadPermissions();
                }
            });

        }
        super.onCreate(savedInstanceState);
    }


   /* private void setProfileToView(JSONObject jsonObject) {
        try {

            profilePictureView.setProfileId(jsonObject.getString("id"));
            infoLayout.setVisibility(View.VISIBLE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }*/

    private void getFBuser(String userId) {

        RestAdapter adapter = new RestAdapter(getApplicationContext(),hostname);
        LogInViewModel.UserRepository userRepository = adapter.createRepository(LogInViewModel.UserRepository.class);

        userRepository.findById(userId, new ObjectCallback<LogInViewModel.User>() {
            @Override
            public void onSuccess(LogInViewModel.User object) {

                ExploreActivity.setPreferences(ExploreActivity.USER_NAME,object.getName());
                ExploreActivity.setPreferences(ExploreActivity.USER_SURNAME,object.getSurname());
                ExploreActivity.setPreferences(ExploreActivity.USER_EMAIL,object.getEmail());
               // ExploreActivity.setPreferences(ExploreActivity.USER_PHOTO_URL,object.getPictureUrl());

                Log.e("FB result name: ",object.getName());
                Log.e("FB result name: ",object.getSurname());
                Log.e("FB result name: ",object.getEmail());
                Log.e("FB result name: ",object.getPictureUrl());
            }

            @Override
            public void onError(Throwable t) {
            }
        });

      /*  GraphRequestAsyncTask request = new GraphRequest(
                AccessToken.getCurrentAccessToken(),
                "/{user-id}/picture",
                null,
                HttpMethod.GET,
                new GraphRequest.Callback() {
                    public void onCompleted(GraphResponse response) {
                    }
                }
        ).executeAsync();
        */
        //Bitmap bitmap = getFacebookProfilePicture(userId);

    }

    public static Bitmap getFacebookProfilePicture(String userID) throws IOException {
        URL imageURL = new URL("https://graph.facebook.com/" + userID + "/picture?type=large");
        Bitmap bitmap = BitmapFactory.decodeStream(imageURL.openConnection().getInputStream());

        return bitmap;
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
        Intent intent = new Intent(LogInActivity.this, ExploreActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.top_bottom_anim,R.anim.alpha_down);
    }

    private void loginWithReadPermissions() {
        LoginManager.getInstance()
                .logInWithReadPermissions(this, Arrays.asList("email"));
    }

}

