package com.example.questionnaire_v3.ui.selectedQuestion;

import android.view.MotionEvent;
import android.view.View;

import com.example.questionnaire_v3.R;

public class SelectedQuestionViewModel {


    public void onTouch(View v, MotionEvent event) {

        if(event.getAction() == MotionEvent.ACTION_DOWN){
            // При нажатии изменяется на фоновое изображение
            v.setBackgroundResource(R.drawable.button_rounded_corners_pressed);
        }else if(event.getAction() == MotionEvent.ACTION_UP){
            // Изменить на изображение при поднятии
            v.setBackgroundResource(R.drawable.button_rounded_corners);
        }
    }
}
