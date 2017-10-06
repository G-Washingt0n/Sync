package com.gnyblecraft.marcul.data;


import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by AndroidDeveloper on 28.09.17.
 */

public interface RestApi {

    @POST("users")
    Observable<ResponseCreateProfile> createProfile(@Body CreateProfile profile);


}