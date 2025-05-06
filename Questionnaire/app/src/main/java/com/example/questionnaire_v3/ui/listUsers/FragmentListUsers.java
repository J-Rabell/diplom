package com.example.questionnaire_v3.ui.listUsers;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.questionnaire_v3.R;


public class FragmentListUsers extends Fragment {

    private NavController navController;
    ImageButton btn_delete_user;
    public FragmentListUsers() {
        // Required empty public constructor
    }

    public static FragmentListUsers newInstance(String param1, String param2) {
        FragmentListUsers fragment = new FragmentListUsers();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v =  inflater.inflate(R.layout.fragment_list_users, container, false);

        btn_delete_user = v.findViewById(R.id.btn_list_user_delete);
        ImageButton btn_edit_user = v.findViewById(R.id.btn_list_user_edit);

        ImageButton btn_search_user = v.findViewById(R.id.btn_searchUser);
        this.navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);

        btn_delete_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                showAlertDialog_DeleteQuestion("Удалить пользователя Таня?");

            }
        });

        btn_edit_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                navController.navigate(R.id.nav_account);
            }
        });

        btn_search_user.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                v.findViewById(R.id.id_user1).setVisibility(View.GONE);
                v.findViewById(R.id.id_user2).setVisibility(View.GONE);
                //loading_all_q.setVisibility(View.VISIBLE);
            }
        });

        btn_search_user.setOnTouchListener(new View.OnTouchListener(){

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){

                    // При нажатии изменяется на фоновое изображение

                    v.setBackgroundResource(R.drawable.button_rounded_corners_pressed);

                }else if(event.getAction() == MotionEvent.ACTION_UP){

                    // Изменить на изображение при поднятии

                    v.setBackgroundResource(R.drawable.button_rounded_corners);

                }
                return false;
            }
        });



        // Inflate the layout for this fragment
        return v;
    }

    public void showAlertDialog_DeleteQuestion(String text) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage(text);
        // add the buttons
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}