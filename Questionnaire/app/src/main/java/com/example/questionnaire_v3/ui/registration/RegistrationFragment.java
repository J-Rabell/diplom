package com.example.questionnaire_v3.ui.registration;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.questionnaire_v3.R;
import com.example.questionnaire_v3.ui.login.LoginFragment;
import com.google.android.material.textfield.TextInputEditText;

public class RegistrationFragment extends Fragment  {

    private RegistrationViewModel mViewModel;
    private TextInputEditText password;
    private TextInputEditText email;
    private TextInputEditText nickname;
    private TextView textRegister;

    public static RegistrationFragment newInstance() {
        return new RegistrationFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(RegistrationViewModel.class);
        mViewModel.context = getActivity().getApplicationContext();


        mViewModel.statusMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String changedValue) {

                if(changedValue!=null)
                    textRegister.setText(changedValue);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.fragment_registration, container, false);
        Button goToLogin = (Button) V.findViewById(R.id.button_go_to_login);
        Button btn_registration = (Button) V.findViewById(R.id.button_register);

        nickname = (TextInputEditText) V.findViewById(R.id.reg_edit_nickname);
        password = (TextInputEditText) V.findViewById(R.id.reg_edit_password);
        email = (TextInputEditText) V.findViewById(R.id.reg_edit_email);
        textRegister = (TextView) V.findViewById(R.id.text_register);

        goToLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment login = new LoginFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_activity_reg_log, login);

                fragmentTransaction.commit();
            }
        });


        btn_registration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

              if(mViewModel.Registration(nickname.getText().toString(), email.getText().toString(), password.getText().toString())){
                  Fragment login = new LoginFragment();
                  Bundle bundle = new Bundle();
                  bundle.putString("message", "Перейдите по ссылке на почте для ее подтверждения!");
                  login.setArguments(bundle);
                  FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                  FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                  fragmentTransaction.replace(R.id.container_activity_reg_log, login);

                  fragmentTransaction.commit();

              }
              else{
                 textRegister.setText(mViewModel.statusMessage.getValue());

              }
            }
        });

        return V;

    }

}