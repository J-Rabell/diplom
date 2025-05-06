package com.example.questionnaire_v3;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.questionnaire_v3.connection.ApiUtils;
import com.example.questionnaire_v3.connection.ServerAPI;
import com.example.questionnaire_v3.data.POJO.User;
import com.google.android.material.navigation.NavigationView;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.questionnaire_v3.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    private ServerAPI mService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mService = ApiUtils.getServerApi();//

        setSupportActionBar(binding.appBarMain.toolbar);
        /*binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() { // кнопка поверху всего
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
             //      R.id.nav_home,
                R.id.nav_account,
                R.id.nav_all_questions,
                R.id.nav_my_questions,
                R.id.nav_create_question,
                R.id.nav_answered_questions,
                R.id.nav_create_question,
                R.id.nav_selected_question,
                R.id.nav_list_user
                )
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/
   public void loadAnswers() {
       mService.getAllUsers().enqueue(new Callback<List<User>>() {
           @Override
           public void onResponse(Call<List<User>> call, Response<List<User>> response) {

               if(response.isSuccessful()) {
                   Log.d("MainActivity", "\n \n \n posts loaded from API \n\n\n  " + response.body().get(0).getEmail() );

               }else {
                   int statusCode = response.code();
                   Log.d("LOADANSWERS", "\n \n \n posts loaded from API \n\n\n  " + response.message() );
                   // handle request errors depending on status code
               }
           }

           @Override
           public void onFailure(Call<List<User>> call, Throwable t) {

               Log.d("MainActivity", "error loading from API \n" + t.getMessage().toString());

           }
       });
   }
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}