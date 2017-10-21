package com.gnyblecraft.marcul.data;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by AndroidDeveloper on 28.09.17.
 */

public interface RestApi {

    @POST("api/users")
    Observable<ResponseCreateProfile> createProfile(@Body CreateProfile profile);

    @GET("auth/facebook-token/callback?")
    Observable<ResponseFBProfile> createFBProfile(@Query("access_token") String profile);


}