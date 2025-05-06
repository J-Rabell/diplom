package com.example.questionnaire_v3.ui.editQuestion;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import com.example.questionnaire_v3.R;

public class EditQuestionFragment extends Fragment {

    String[] categories = {"Аниме", "Видеоигры", "Дорамы", "Другое", "Животные", "Знаменитости", "Мода", "Музыка", "Окружающий мир","Работа", "Семья", "Сериалы", "Спорт"};
    private Spinner spinner;
    ImageButton btn_editImage;
    public EditQuestionFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static EditQuestionFragment newInstance(String param1, String param2) {
        EditQuestionFragment fragment = new EditQuestionFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_edit_question, container, false);
        // RecyclerView recyclerView = view.findViewById(R.id.recyclerView_questions);//
        btn_editImage = v.findViewById(R.id.btn_edit_image);
        spinner = v.findViewById(R.id.spinner_category_edit);

        btn_editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, 1);
            }
        });


        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, categories);

        // Определяем разметку для использования при выборе элемента
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(4);
        return v;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1
                && resultCode == Activity.RESULT_OK) {
            String path = getPathFromCameraData(data, requireContext());
            Log.i("PICTURE", "Path: " + path);
            if (path != null) {
                Bitmap bMap = BitmapFactory.decodeFile(path);
                // avatar.setImageBitmap(bMap);
                btn_editImage.setImageBitmap(bMap);
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