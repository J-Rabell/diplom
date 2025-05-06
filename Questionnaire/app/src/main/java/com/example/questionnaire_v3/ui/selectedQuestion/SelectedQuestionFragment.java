package com.example.questionnaire_v3.ui.selectedQuestion;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.questionnaire_v3.R;
import com.example.questionnaire_v3.databinding.FragmentAccountBinding;
import com.example.questionnaire_v3.databinding.FragmentSelectedQuestionBinding;

public class SelectedQuestionFragment extends Fragment {

    private NavController navController;
    private SelectedQuestionViewModel mViewModel;
    private int rating = 0;
    ImageButton btnDelete;

    public SelectedQuestionFragment() {
        // Required empty public constructor
    }
    public static SelectedQuestionFragment newInstance(String param1, String param2) {
        SelectedQuestionFragment fragment = new SelectedQuestionFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = new SelectedQuestionViewModel();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentSelectedQuestionBinding binding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_selected_question, container, false);
        binding.setLifecycleOwner(this);
        binding.setSelectedQuestionViewModel(mViewModel);

        View V = binding.getRoot();
        ImageButton btnComment = (ImageButton) V.findViewById(R.id.btn_comment);
        ImageButton btnAnalytics = (ImageButton) V.findViewById(R.id.btn_analytics);
        btnDelete = (ImageButton) V.findViewById(R.id.btn_delete);
        ImageButton btnEdit = (ImageButton) V.findViewById(R.id.btn_edit_question);

        TextView answer1 = (TextView) V.findViewById(R.id.selected_question_answer_1);
        TextView answer2 = (TextView) V.findViewById(R.id.selected_question_answer_2);
        TextView answer3 = (TextView) V.findViewById(R.id.selected_question_answer_3);
        TextView answer4 = (TextView) V.findViewById(R.id.selected_question_answer_4);
        TextView answer5 = (TextView) V.findViewById(R.id.selected_question_answer_5);

        TextView rating_question = (TextView) V.findViewById(R.id.selected_question_rating);
        ImageButton btnLike = (ImageButton) V.findViewById(R.id.btn_like_question);
        ImageButton btnDislike = (ImageButton) V.findViewById(R.id.btn_dislike_question);

        this.navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);
        btnComment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnComment.setAlpha(0.3F);
                Log.d("AAAAAAAAAAA", "COMMENT");
                navController.navigate(R.id.nav_comments);
            }
        });
        btnAnalytics.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnAnalytics.setAlpha(0.3F);
                Log.d("AAAAAAAAAAA", "ANALYTICS");
                navController.navigate(R.id.nav_analytics);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnDelete.setAlpha(0.3F);
                Log.d("AAAAAAAAAAA", "Delete");
                showAlertDialog_DeleteQuestion("Вы действительно хотите удалить опрос?");

            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                btnEdit.setAlpha(0.3F);
                Log.d("AAAAAAAAAAA", "Delete");
               navController.navigate(R.id.nav_edit_question);
            }
        });

        btnLike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(btnLike.getAlpha() > 0.9F) {
                    btnLike.setAlpha(0.5F);
                    rating_question.setText("0");
                }
                else {
                    btnLike.setAlpha(1F);
                    btnDislike.setAlpha(0.5F);
                    rating_question.setText("1");
                }
            }
        });
        btnDislike.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(btnDislike.getAlpha() > 0.9F) {
                    btnDislike.setAlpha(0.5F);
                    rating_question.setText("0");
                }
                else {
                    btnDislike.setAlpha(1F);
                    btnLike.setAlpha(0.5F);
                    rating_question.setText("-1");
                }
            }
        });

       answer1.setOnTouchListener(new View.OnTouchListener(){
           @Override
           public boolean onTouch(View v, MotionEvent event) {
               answer1.setText("1 / Кошек");
               answer1.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_700));
               answer1.setTypeface(null,Typeface.BOLD);
               answer2.setText("0 / Собак");
               answer2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
               answer2.setTypeface(null,Typeface.NORMAL);
               answer3.setText("0 / Осень");
               answer3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
               answer3.setTypeface(null,Typeface.NORMAL);
               answer4.setText("0 / Весна");
               answer4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
               answer4.setTypeface(null,Typeface.NORMAL);
               answer5.setText("0 / Все-равно какая пора года");
               answer5.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
               answer5.setTypeface(null,Typeface.NORMAL);
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

        answer2.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                answer1.setText("0 / Кошек");
                answer1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                answer1.setTypeface(null,Typeface.NORMAL);
                answer2.setText("1 / Собак");
                answer2.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_700));
                answer2.setTypeface(null,Typeface.BOLD);
                answer3.setText("0 / Осень");
                answer3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                answer3.setTypeface(null,Typeface.NORMAL);
                answer4.setText("0 / Весна");
                answer4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                answer4.setTypeface(null,Typeface.NORMAL);
                answer5.setText("0 / Все-равно какая пора года");
                answer5.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                answer5.setTypeface(null,Typeface.NORMAL);
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
        answer3.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                answer1.setText("0 / Лето");
                answer1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                answer1.setTypeface(null,Typeface.NORMAL);
                answer2.setText("0 / Зима");
                answer2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                answer2.setTypeface(null,Typeface.NORMAL);
                answer3.setText("1 / Осень");
                answer3.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_700));
                answer3.setTypeface(null,Typeface.BOLD);
                answer4.setText("0 / Весна");
                answer4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                answer4.setTypeface(null,Typeface.NORMAL);
                answer5.setText("0 / Все-равно какая пора года");
                answer5.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                answer5.setTypeface(null,Typeface.NORMAL);
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

        answer4.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    answer1.setText("0 / Лето");
                    answer1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                    answer1.setTypeface(null,Typeface.NORMAL);
                    answer2.setText("0 / Зима");
                    answer2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                    answer2.setTypeface(null,Typeface.NORMAL);
                    answer3.setText("0 / Осень");
                    answer3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                    answer3.setTypeface(null,Typeface.NORMAL);
                    answer4.setText("1 / Весна");
                    answer4.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_700));
                    answer4.setTypeface(null,Typeface.BOLD);
                    answer5.setText("0 / Все-равно какая пора года");
                    answer5.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                    answer5.setTypeface(null,Typeface.NORMAL);
                    // При нажатии изменяется на фоновое изображение
                    v.setBackgroundResource(R.drawable.button_rounded_corners_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    // Изменить на изображение при поднятии
                    v.setBackgroundResource(R.drawable.button_rounded_corners);
                }
                return false;
            }
        });

        answer5.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    answer1.setText("0 / Лето");
                    answer1.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                    answer1.setTypeface(null,Typeface.NORMAL);
                    answer2.setText("0 / Зима");
                    answer2.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                    answer2.setTypeface(null,Typeface.NORMAL);
                    answer3.setText("0 / Осень");
                    answer3.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                    answer3.setTypeface(null,Typeface.NORMAL);
                    answer4.setText("0 / Весна");
                    answer4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                    answer4.setTypeface(null,Typeface.NORMAL);
                    answer5.setText("1 / Все-равно какая пора года");
                    answer5.setTextColor(ContextCompat.getColor(requireContext(), R.color.purple_700));
                    answer5.setTypeface(null,Typeface.BOLD);
                    // При нажатии изменяется на фоновое изображение
                    v.setBackgroundResource(R.drawable.button_rounded_corners_pressed);
                }else if(event.getAction() == MotionEvent.ACTION_UP){
                    // Изменить на изображение при поднятии
                    v.setBackgroundResource(R.drawable.button_rounded_corners);
                }
                return false;
            }
        });

        answer4.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                answer4.setText("0 / Весна");
                answer4.setTextColor(ContextCompat.getColor(requireContext(), R.color.black));
                answer4.setTypeface(null,Typeface.NORMAL);
                return false;
            }
        });


        return V;

    }


    public void showAlertDialog_DeleteQuestion(String text) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage(text);
        // add the buttons
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnDelete.setAlpha(1F);
               // deleteAccount();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                btnDelete.setAlpha(1F);
                // deleteAccount();
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}