package com.example.questionnaire_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        startHeavyProcessing();

    }

    private void startHeavyProcessing(){
        new LongOperation().execute("");
    }

    private class LongOperation extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            //some heavy processing resulting in a Data String

            return "whatever result you have";
        }

        @Override
        protected void onPostExecute(String result) {
//посылать рефрештокен чтобы сразу ассеsтокен обновлять.. а если там рефрештокен не оч, то на страницу регистрации кидать
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

           if(preferences.getBoolean("userRegistered", false))
            {
            Intent i = new Intent(SplashScreen.this, MainActivity.class);
           // i.putExtra("data", result);
            startActivity(i);
            finish();
            }
           else {
               Intent i = new Intent(SplashScreen.this,ActivityRegLog.class);
             //  i.putExtra("data", result);
               startActivity(i);
               finish();
           }

        }

    }
}