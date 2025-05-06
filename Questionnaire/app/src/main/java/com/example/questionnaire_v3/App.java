package com.example.questionnaire_v3;

import android.app.Application;

import androidx.room.Room;

import com.example.questionnaire_v3.connection.ConnectionInternet;
import com.example.questionnaire_v3.database.AppDatabase;

public class App extends Application {

    public static App instance;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();

    }

    public static App getInstance() {
        return instance;
    }

    public AppDatabase getDatabase() {
        return database;
    }

}