package com.gnyblecraft.marcul.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by lenovo on 09.10.2017.
 */

public class FBProfile {
    @SerializedName("access_token")
    private String accessToken;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
