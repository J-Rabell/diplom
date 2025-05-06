package com.example.questionnaire_v3.ui.account;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.questionnaire_v3.App;
import com.example.questionnaire_v3.R;
import com.example.questionnaire_v3.connection.ConnectionInternet;
import com.example.questionnaire_v3.databinding.FragmentAccountBinding;
import com.example.questionnaire_v3.ui.login.LoginFragment;
import com.example.questionnaire_v3.ui.login.LoginViewModel;
import com.squareup.picasso.Picasso;

public class AccountFragment extends Fragment {

    private NavController navController;
    public AccountViewModel mViewModel;
    private ConnectionInternet conn;
    private static final int PICK_IMAGE = 1;
    ImageView avatar;
    ImageButton btn_chooseImg;
    public AccountFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel =  new ViewModelProvider(this).get(AccountViewModel.class);
        mViewModel.context = requireContext();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAccountBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_account, container, false);
        binding.setLifecycleOwner(this);
        binding.setAccountViewModel(mViewModel);

        View v = binding.getRoot();//inflater.inflate(R.layout.fragment_account, container, false);


        Button btn_my_achievements = (Button) v.findViewById(R.id.btn_my_achievements);
        Button btn_change_role = (Button) v.findViewById(R.id.btn_change_role);

        btn_chooseImg = (ImageButton) v.findViewById(R.id.image_change_avatar);
        RelativeLayout visibility_loadingPanel = (RelativeLayout) v.findViewById(R.id.loadingPanel_account);
        avatar = (ImageView) v.findViewById(R.id.image_avatar);

        btn_my_achievements.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
                navController.navigate(R.id.nav_my_achievements);
            }
        });

        btn_change_role.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showAlertDialog_ChangeRole("Изменить роль на admin?");
            }
        });

        btn_chooseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PICK_IMAGE);
            }
        });

        return v;
    }


    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE
                && resultCode == Activity.RESULT_OK) {
            String path = getPathFromCameraData(data, requireContext());
            Log.i("PICTURE", "Path: " + path);
            if (path != null) {
                Bitmap bMap = BitmapFactory.decodeFile(path);
               // avatar.setImageBitmap(bMap);
                btn_chooseImg.setImageBitmap(bMap);
                mViewModel.imagePatch = path;
            }
        }
    }

    public static String getPathFromCameraData(Intent data, Context context) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);
        cursor.close();
        return picturePath;
    }

    public void showAlertDialog_ChangeRole(String text) {
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
    @Override
    public void onStart() {
        super.onStart();

        Log.d("Fragment 1", "onStart");
        mViewModel.initAccount();
         Picasso.with(requireContext())
                .load("https://server-g56xonta2-xrabell13.vercel.app/e4vu0kvqn-o.jpg")
                .placeholder(R.drawable.ic_menu_account)
                .error(R.drawable.ic_menu_account)
                .into(avatar);
        Picasso.with(requireContext())
                .load("https://server-g56xonta2-xrabell13.vercel.app/e4vu0kvqn-o.jpg")
                .placeholder(R.drawable.ic_menu_account)
                .error(R.drawable.ic_menu_account)
                .into(btn_chooseImg);
    }

}