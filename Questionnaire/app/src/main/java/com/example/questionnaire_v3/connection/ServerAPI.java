package com.example.questionnaire_v3.connection;

import com.example.questionnaire_v3.data.POJO.RegistrationInfo;
import com.example.questionnaire_v3.data.POJO.User;
import com.example.questionnaire_v3.data.POJO.response.AccountResponse;
import com.example.questionnaire_v3.data.POJO.response.LoginInfo;
import com.example.questionnaire_v3.data.POJO.response.RegistrationInfoResponse;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ServerAPI {
    @GET("user/users")
    Call<List<User>> getAllUsers();
    //данные, возвращаемые при регистрации и авторизации одинаковы, потому один класс
    @POST("user/registration")
    Call<RegistrationInfoResponse> registration(@Body RegistrationInfo registrationInfo);
    @POST("user/login")
    Call<RegistrationInfoResponse> login(@Body LoginInfo loginInfo);
    @POST("user/logout")
    Call<Integer> logout(@Body LoginInfo loginInfo);
    @DELETE("user/{userId}")
    Call<Integer> deleteUserAccount(@Path("userId") String var);

    @Multipart
    @PUT("account/{userId}")
    Call<AccountResponse> updateUserAccount(@Path("userId") String var,
                                            @Part("nickname") RequestBody nickname,
                                            @Part("description") RequestBody password,
                                            @Part MultipartBody.Part filePart);

    @GET("account/{userId}")
    Call<AccountResponse> getUserAccount(@Path("userId") String var);
}
