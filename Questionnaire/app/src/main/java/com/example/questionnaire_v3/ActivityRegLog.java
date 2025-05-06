package com.example.questionnaire_v3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.questionnaire_v3.ui.registration.RegistrationFragment;

public class ActivityRegLog extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_log);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container_activity_reg_log, RegistrationFragment.newInstance())
                    .commitNow();
        }
    }

    @Override
    public void onBackPressed() {
    }
}