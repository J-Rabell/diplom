package com.example.questionnaire_v3.connection;

public class ApiUtils {
    private static final String BASE_URL = "https://server-fws4fscjb-xrabell13.vercel.app/api/";
    public static ServerAPI getServerApi() {
        return RetrofitClient.getClient(BASE_URL).create(ServerAPI.class);
    }
}
