package com.example.questionnaire_v3.ui.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.questionnaire_v3.MainActivity;
import com.example.questionnaire_v3.R;
import com.example.questionnaire_v3.ui.registration.RegistrationFragment;
import com.example.questionnaire_v3.ui.registration.RegistrationViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class LoginFragment extends Fragment{
    private TextInputEditText password;
    private TextInputEditText email;
    private LoginViewModel mViewModel;
    private TextView textLogin;
    private String message;

    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        mViewModel.context = getActivity().getApplicationContext();
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            message = bundle.getString("message");
        }

        mViewModel.statusMessage.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String changedValue) {
                if(changedValue!=null)
                    textLogin.setText(changedValue);
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View V = inflater.inflate(R.layout.fragment_login, container, false);
        Button goToRegistration = (Button) V.findViewById(R.id.button_go_to_registration);
        Button btn_login = (Button) V.findViewById(R.id.button_login);

        password = (TextInputEditText) V.findViewById(R.id.log_edit_password);
        email = (TextInputEditText) V.findViewById(R.id.log_edit_email);
        textLogin = (TextView) V.findViewById(R.id.text_login);
        textLogin.setText(message);

        goToRegistration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment registrationFragment = new RegistrationFragment();
                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.container_activity_reg_log, registrationFragment);

                fragmentTransaction.commit();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                mViewModel.Login(email.getText().toString(), password.getText().toString());

            }
        });

        mViewModel.responseSuccessful.observe(getActivity(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean changedValue) {
                if(changedValue) {
                    Intent i = new Intent(getContext(), MainActivity.class);
                    startActivity(i);
                }
            }
        });
        return V;
    }

}