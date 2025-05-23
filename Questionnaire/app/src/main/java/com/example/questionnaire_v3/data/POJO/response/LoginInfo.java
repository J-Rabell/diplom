package com.example.questionnaire_v3.data.POJO.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginInfo {

    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("password")
    @Expose
    private String password;

    public LoginInfo() {
    }

    public LoginInfo(String email, String password) {
        super();
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}