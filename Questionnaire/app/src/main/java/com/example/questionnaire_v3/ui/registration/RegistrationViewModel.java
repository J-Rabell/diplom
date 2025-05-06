package com.example.questionnaire_v3.ui.registration;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.questionnaire_v3.MainActivity;
import com.example.questionnaire_v3.connection.ApiUtils;
import com.example.questionnaire_v3.connection.ServerAPI;
import com.example.questionnaire_v3.data.POJO.RegistrationInfo;
import com.example.questionnaire_v3.data.POJO.response.RegistrationInfoResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationViewModel extends ViewModel {

    Context context;
    private ServerAPI mService;

    private Boolean responseSuccessful = false;

    public MutableLiveData<String> statusMessage = new MutableLiveData<String>(null);


    public RegistrationViewModel(){

        mService = ApiUtils.getServerApi();

    }

    public boolean Registration(String nickname, String email, String password){

        statusMessage.setValue("");
        if(checkNickname(nickname) && checkEmail(email) && checkPassword(password))
        {
            mService.registration(new RegistrationInfo(nickname, email.toLowerCase(), password))
                    .enqueue(new Callback<RegistrationInfoResponse>() {
                @Override
                public void onResponse(Call<RegistrationInfoResponse> call, Response<RegistrationInfoResponse> response) {

                    if(response.isSuccessful()) {

                        statusMessage.setValue("Перейдите по ссылке на почте для ее подтверждения!");
                        responseSuccessful = true;

                    }else {
                        try {
                            JSONObject obj = new JSONObject(response.errorBody().string());
                            Log.d("MainActivity", "\nposts loaded from API "+obj.getString("message")+" \n");
                            statusMessage.setValue(obj.getString("message"));

                        } catch (Exception e) {
                            Log.d("MainActivity", "\n"+e.getMessage()+"\n");
                        }
                    }
                }

                @Override
                public void onFailure(Call<RegistrationInfoResponse> call, Throwable t) {

                    Log.d("MainActivity", "error loading from API \n" + t.getMessage().toString());

                }
            });
            return responseSuccessful;
        }

        return false;
    }

    boolean checkNickname(String nickname)
    {
        if(nickname.length() < 20 && !(nickname.isEmpty())) {
            return true;
        }
        else {
            statusMessage.setValue(statusMessage.getValue() + "Количество символов в нике должно быть меньше 20 символов.\n");
            return false;
        }
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