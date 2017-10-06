package com.gnyblecraft.marcul.domain;

import com.gnyblecraft.marcul.data.CreateProfile;
import com.gnyblecraft.marcul.data.ResponseCreateProfile;
import com.gnyblecraft.marcul.data.RestService;
import com.gnyblecraft.marcul.domain.entity.CreateProfileModel;
import com.gnyblecraft.marcul.domain.entity.ResponseCreateProfileModel;
import com.gnyblecraft.marcul.domain.usecases.base.UseCase;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * Created by AndroidDeveloper on 28.09.17.
 */

public class CreateProfileUseCase extends UseCase<CreateProfileModel, ResponseCreateProfileModel> {
    @Override
    protected Observable<ResponseCreateProfileModel> buildUseCase(CreateProfileModel param) {

        CreateProfile profile = new CreateProfile();
        profile.setName(param.getName());
        profile.setSurname(param.getSurname());
        profile.setEmail(param.getEmail());
        profile.setPassword(param.getPassword());
        profile.setPhone(param.getPhone());

        return RestService.getInstance().saveProfile(profile).map(new Function<ResponseCreateProfile, ResponseCreateProfileModel>() {
            @Override
            public ResponseCreateProfileModel apply(@NonNull ResponseCreateProfile responseCreateProfile) throws Exception {
                return convert(responseCreateProfile);
            }
        });
    }

    private ResponseCreateProfileModel convert(ResponseCreateProfile responseCreateProfile){
        ResponseCreateProfileModel responseCreateProfileModel = new ResponseCreateProfileModel();
        responseCreateProfileModel.setName(responseCreateProfile.getName());
        responseCreateProfileModel.setSurname(responseCreateProfile.getSurname());
        responseCreateProfileModel.setEmail(responseCreateProfile.getEmail());
        responseCreateProfileModel.setPhone(responseCreateProfile.getPhone());
        responseCreateProfileModel.setUserId(responseCreateProfile.getUserId());

        return responseCreateProfileModel;
    }




}

