package com.gnyblecraft.marcul.domain.entity;

/**
 * Created by lenovo on 09.10.2017.
 */

public class ResponseFBModel {
    private String access_token;
    private String userId;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
