package com.example.questionnaire_v3.ui.account;

import static androidx.core.app.ActivityCompat.startActivityForResult;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.questionnaire_v3.ActivityRegLog;
import com.example.questionnaire_v3.R;
import com.example.questionnaire_v3.connection.ApiUtils;
import com.example.questionnaire_v3.connection.ConnectionInternet;
import com.example.questionnaire_v3.connection.ServerAPI;
import com.example.questionnaire_v3.data.POJO.response.AccountResponse;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountViewModel extends ViewModel {
    Context context;
    private ConnectionInternet conn;
    private ServerAPI mService;
    private SharedPreferences preferences;
    public MutableLiveData<Boolean> responseSuccessful = new MutableLiveData<Boolean>(false);
    public MutableLiveData<Boolean> visibilityChangeAccount = new MutableLiveData<Boolean>(false);

    public MutableLiveData<String> description = new MutableLiveData<String>("");
    public MutableLiveData<String> nickname = new MutableLiveData<String>("");
  //  public MutableLiveData<ImageView> avatar = new MutableLiveData<ImageView>("");

    public String imagePatch = null;

    private static final String REFRESH_TOKEN_NAME = "refreshToken";
    private static final String ACCESS_TOKEN_NAME = "accessToken";
    public AccountViewModel(){

        mService = ApiUtils.getServerApi();
        conn = new ConnectionInternet();

    }

    public boolean initAccount(){
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String userId = preferences.getString("id", null);
        responseSuccessful.setValue(false);

        if(conn.isInternetAvailable(context) &&  userId!=null)
        {
            try {
                mService.getUserAccount(userId)
                        .enqueue(new Callback<AccountResponse>() {
                            @Override
                            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {

                                if (response.isSuccessful()) {
                                    if(response.body().getDescription()!=null){
                                    description.setValue(response.body().getDescription());
                                    }
                                    else description.setValue("Нет описания...");
                                    nickname.setValue(response.body().getNickname());

                                   // imagePatch =  "https://server-g56xonta2-xrabell13.vercel.app/" + response.body().getImageUrl();


                                } else {
                                    try {
                                        JSONObject obj = new JSONObject(response.errorBody().string());

                                    } catch (Exception e) {
                                        Log.d("AccountViewModel", "\n" + e.getMessage() + "\n");
                                    }
                                    // handle request errors depending on status code
                                }

                                responseSuccessful.setValue(response.isSuccessful());
                            }

                            @Override
                            public void onFailure(Call<AccountResponse> call, Throwable t) {

                                Log.d("MainActivity", "error loading from API \n" + t.getMessage().toString());

                            }
                        });
                return responseSuccessful.getValue();
            }catch (Exception e){
                Log.d("MainActivity", "\n" + e.getMessage());
            }
        }
        else {

        }
        return false;
    }

    public void deleteAccount(){

        String userId = preferences.getString("id", null);

        if(conn.isInternetAvailable(context) &&  userId!=null)
        {
            try {
                mService.deleteUserAccount(userId)
                        .enqueue(new Callback<Integer>() {
                            @Override
                            public void onResponse(Call<Integer> call, Response<Integer> response) {

                                if (response.isSuccessful()) {
                                    if(response.body().intValue()==1) {
                                        logout();
                                        Intent i = new Intent(context, ActivityRegLog.class);
                                        context.startActivity(i);
                                    }
                                    else {
                                        Log.d("Account", "Не удалось удалить аккаунт");
                                    }
                                } else {
                                    try {
                                        JSONObject obj = new JSONObject(response.errorBody().string());

                                    } catch (Exception e) {
                                        Log.d("AccountViewModel", "\n" + e.getMessage() + "\n");
                                    }
                                    // handle request errors depending on status code
                                }

                                responseSuccessful.setValue(response.isSuccessful());

                            }

                            @Override
                            public void onFailure(Call<Integer> call, Throwable t) {

                                Log.d("MainActivity", "error loading from API \n" + t.getMessage().toString());

                            }
                        });
            }catch (Exception e){
                Log.d("MainActivity", "\n" + e.getMessage());
            }
        }
        else {

        }


    }

    public void saveChangeAccount(){
        String userId = preferences.getString("id", null);
      //  RequestBody reqFile = null; // RequestBody.create(MediaType.parse("image/jpeg"),);
        MultipartBody.Part filePart = null;
        RequestBody nickname_data = RequestBody.create(MediaType.parse("text/plain"),
                this.nickname.getValue());
        RequestBody description_data = RequestBody.create(MediaType.parse("text/plain"),
                this.description.getValue());

        if(imagePatch!=null){
            File file = new File(imagePatch);
            filePart = MultipartBody.Part.createFormData("img", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));
        }
        Call<AccountResponse> call = mService.updateUserAccount(userId,nickname_data,
                description_data,
                filePart);
        call.enqueue(new Callback<AccountResponse>() {

            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    description.setValue(response.body().getDescription());
                    nickname.setValue(response.body().getNickname());
                    Log.d("AAAAAAAAAAAAAAAAAA", "WORKING!!");
                } else {
                    try {
                        JSONObject obj = new JSONObject(response.errorBody().string());

                    } catch (Exception e) {
                        Log.d("AccountViewModel", "\n" + e.getMessage() + "\n");
                    }
                    // handle request errors depending on status code
                }
                responseSuccessful.setValue(true);

            }

            @Override
            public void onFailure(Call<AccountResponse> call,
                                  Throwable t) {

            }
        });

    }
    public void logout(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }
    public void onClick_Logout(View v) {
        conn = new ConnectionInternet();
        if(conn.isInternetAvailable(context)){
            showAlertDialog_Logout("Вы уверены, что хотите выйти?");
        }
        else {
            Log.d("INTERNET", "\nNO");
        }
    }
    public void onClick_DeleteAccount(View v) {
        conn = new ConnectionInternet();
        if(conn.isInternetAvailable(context)){
            showAlertDialog_DeleteAccount("Вы уверены, что хотите удалить учетную запись навсегда?");
        }
        else {
            Log.d("INTERNET", "\nNO");
        }
    }


    public void onClick_UpdateAccount(View v) {
         visibilityChangeAccount.setValue(true);
    }
    public void onClick_CloseUpdateAccount(View v) {
            visibilityChangeAccount.setValue(false);
    }
    public void onClick_SaveChangeAccount(View v) {
       //

        if(description.getValue()!=""&&nickname.getValue()!="") {
            responseSuccessful.setValue(false);
            saveChangeAccount();
            visibilityChangeAccount.setValue(false);
        }
        else {
            Log.d("AAAAAAAA", "EMPTY");
        }

    }

    public  void chooseImage(View v){

      //  avatar.setImageBitmap(BitmapFactory.decodeFile(picturePath));
    }
    public void showAlertDialog_DeleteAccount(String text) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(text);
        // add the buttons
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteAccount();
            }
        });
        builder.setNegativeButton("Нет", null);
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void showAlertDialog_Logout(String text) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(text);
        // add the buttons
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                logout();
                Intent i = new Intent(context, ActivityRegLog.class);
                context.startActivity(i);
            }
        });
        builder.setNegativeButton("Нет", null);
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
