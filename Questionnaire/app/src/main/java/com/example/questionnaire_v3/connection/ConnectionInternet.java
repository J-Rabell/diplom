package com.example.questionnaire_v3.connection;


import android.content.Context;
import android.net.ConnectivityManager;

public class ConnectionInternet {
    public boolean isInternetAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
