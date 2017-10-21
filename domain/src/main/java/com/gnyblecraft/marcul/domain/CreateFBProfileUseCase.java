package com.gnyblecraft.marcul.domain;

import android.util.Log;

import com.gnyblecraft.marcul.data.FBProfile;
import com.gnyblecraft.marcul.data.ResponseFBProfile;
import com.gnyblecraft.marcul.data.RestService;
import com.gnyblecraft.marcul.domain.entity.FBModel;
import com.gnyblecraft.marcul.domain.entity.ResponseFBModel;
import com.gnyblecraft.marcul.domain.usecases.base.UseCase;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by AndroidDeveloper on 28.09.17.
 */

public class CreateFBProfileUseCase extends UseCase<FBModel,ResponseFBModel> {
    @Override
    protected Observable<ResponseFBModel> buildUseCase(FBModel param) {

        FBProfile profile = new FBProfile();
        profile.setAccessToken(param.getAccessToken());

        return RestService.getInstance().saveFBProfile(param.getAccessToken()).map(new Function<ResponseFBProfile, ResponseFBModel>() {
            @Override
            public ResponseFBModel apply(@NonNull ResponseFBProfile responseFBProfile) throws Exception {
                return convert(responseFBProfile);
            }
        });
    }

    private ResponseFBModel convert(ResponseFBProfile responseFBProfile){
        Log.e("AAAAAAAAAAA","Convert func call");
        ResponseFBModel responseFBModel = new ResponseFBModel();
        responseFBModel.setAccess_token(responseFBProfile.getAccess_token());
        Log.e("MMMMMMMMMMMMM", responseFBProfile.getUserId());

        responseFBModel.setUserId(responseFBProfile.getUserId());

        return responseFBModel;
    }




}

