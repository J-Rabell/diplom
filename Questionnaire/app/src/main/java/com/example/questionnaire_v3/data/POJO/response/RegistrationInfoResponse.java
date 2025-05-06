package com.example.questionnaire_v3.data.POJO.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.example.questionnaire_v3.data.POJO.User;


public class RegistrationInfoResponse {

    @SerializedName("accessToken")
    @Expose
    private String accessToken;
    @SerializedName("refreshToken")
    @Expose
    private String refreshToken;
    @SerializedName("user")
    @Expose
    public User user;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}

