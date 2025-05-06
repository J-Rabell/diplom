package com.example.questionnaire_v3.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.questionnaire_v3.App;
import com.example.questionnaire_v3.MainActivity;
import com.example.questionnaire_v3.SplashScreen;
import com.example.questionnaire_v3.connection.ApiUtils;
import com.example.questionnaire_v3.connection.ServerAPI;
import com.example.questionnaire_v3.data.POJO.RegistrationInfo;
import com.example.questionnaire_v3.data.POJO.response.LoginInfo;
import com.example.questionnaire_v3.data.POJO.response.RegistrationInfoResponse;
import com.example.questionnaire_v3.database.AppDatabase;
import com.example.questionnaire_v3.database.entity.Account;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    Context context;
    private ServerAPI mService;
    private SharedPreferences preferences;

    private AppDatabase db;
    private static final String REFRESH_TOKEN_NAME = "refreshToken";
    private static final String ACCESS_TOKEN_NAME = "accessToken";
    public MutableLiveData<Boolean> responseSuccessful = new MutableLiveData<Boolean>(false);

    public MutableLiveData<String> statusMessage = new MutableLiveData<String>(null);


    public LoginViewModel(){

        mService = ApiUtils.getServerApi();
     //      db = App.getInstance().getDatabase();

    }
    public boolean Login(String email, String password){

        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        statusMessage.setValue("");

        if(checkEmail(email) && checkPassword(password))
        {
            try {
                mService.login(new LoginInfo(email.toLowerCase(), password))
                        .enqueue(new Callback<RegistrationInfoResponse>() {
                            @Override
                            public void onResponse(Call<RegistrationInfoResponse> call, Response<RegistrationInfoResponse> response) {

                                if (response.isSuccessful()) {
                                    SharedPreferences.Editor editor = preferences.edit();
                                    editor.putString(REFRESH_TOKEN_NAME, response.body().getRefreshToken());
                                    editor.putString(ACCESS_TOKEN_NAME, response.body().getAccessToken());
                                    editor.putString("id", String.valueOf(response.body().getUser().getId()));
                                    editor.putBoolean("userRegistered", true);
                                    editor.apply();
                                    responseSuccessful.setValue(true);
                                    //так же здесь заполняется таблица локальная с данными о пользователе
                                  /*  Account acc = new Account();
                                    acc.userId = response.body().getUser().getId();
                                    acc.email = response.body().getUser().getEmail();
                                    db.accountDao().insert(acc);

                                    Log.d("AAAAAAAAAAAAAAAAAAAAAAA", "\n"+db.accountDao().getOne().email);*/
                                } else {
                                    try {
                                        JSONObject obj = new JSONObject(response.errorBody().string());
                                        statusMessage.setValue(obj.getString("message"));
                                    } catch (Exception e) {
                                        Log.d("LoginViewModel", "\n" + e.getMessage() + "\n");
                                    }
                                    // handle request errors depending on status code
                                }
                            }

                            @Override
                            public void onFailure(Call<RegistrationInfoResponse> call, Throwable t) {

                                Log.d("MainActivity", "error loading from API \n" + t.getMessage().toString());

                            }
                        });
                return responseSuccessful.getValue();
            }catch (Exception e){
                Log.d("MainActivity", "\n" + e.getMessage());
            }
        }
        return false;
    }

    boolean checkEmail(String email)
    {
        if(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return true;
        }
        else {
            statusMessage.setValue(statusMessage.getValue() + "Неверный email.\n");
            return false;
        }
    }
    boolean checkPassword(String password)
    {
        if(password.length() > 3 && password.length()<32) {
            return true;
        }
        else {
            statusMessage.setValue(statusMessage.getValue() + "Количество символов в пароле должно быть от 3 до 32 символов.\n");
            return false;
        }
    }

}
