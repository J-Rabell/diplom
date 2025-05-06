package com.example.questionnaire_v3.ui.creatingQuestion;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.example.questionnaire_v3.R;

public class CreatingQuestionFragment extends Fragment {

    private static final int PICK_IMAGE = 1;
    ImageButton btn_selectImage;
    Button btn_publication;
    LinearLayout l_achievement;

    String[] categories = {"Аниме", "Видеоигры", "Дорамы", "Другое", "Животные", "Знаменитости", "Мода", "Музыка", "Окружающий мир","Работа", "Семья", "Сериалы", "Спорт"};
    private Spinner spinner;
    public CreatingQuestionFragment() {
        // Required empty public constructor
    }

    public static CreatingQuestionFragment newInstance(String param1, String param2) {
        CreatingQuestionFragment fragment = new CreatingQuestionFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_creating_question, container, false);
        btn_selectImage = v.findViewById(R.id.btn_select_imageQuestion);
        l_achievement = v.findViewById(R.id.linear_new_achievement);
        btn_publication = v.findViewById(R.id.btn_publication);
        btn_selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, PICK_IMAGE);
            }
        });

        btn_publication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l_achievement.animate()
                        .translationY(0)
                        .setDuration(5000)
                        .alpha(0.0f)
                        .setListener(new AnimatorListenerAdapter() {
                            public void onAnimationStart(Animator animation)
                            {
                                super.onAnimationEnd(animation);
                                l_achievement.setVisibility(View.VISIBLE);
                            }
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                l_achievement.setVisibility(View.INVISIBLE);
                            }
                        });
            }
        });

        // RecyclerView recyclerView = view.findViewById(R.id.recyclerView_questions);//
        spinner = v.findViewById(R.id.spinner_category_creating);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Определяем разметку для использования при выборе элемента
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(3);//другое
        // Inflate the layout for this fragment
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
                btn_selectImage.setImageBitmap(bMap);
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
}