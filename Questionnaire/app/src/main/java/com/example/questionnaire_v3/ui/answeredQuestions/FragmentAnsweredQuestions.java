package com.example.questionnaire_v3.ui.answeredQuestions;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionnaire_v3.R;
import com.example.questionnaire_v3.adapter.QuestionAdapter;
import com.example.questionnaire_v3.data.model.ListItemQuestion;

import java.util.ArrayList;

public class FragmentAnsweredQuestions extends Fragment {

    ArrayList<ListItemQuestion> questions = new ArrayList<ListItemQuestion>();
    String[] dayOfWeek = {"Аниме", "Видеоигры", "Все", "Дорамы", "Другое", "Животные", "Знаменитости", "Мода", "Музыка", "Окружающий мир","Работа", "Семья", "Сериалы", "Спорт"};
    private Spinner spinner;

    public FragmentAnsweredQuestions() {
        // Required empty public constructor
    }

    public static FragmentAnsweredQuestions newInstance(String param1, String param2) {
        FragmentAnsweredQuestions fragment = new FragmentAnsweredQuestions();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
// Получаем экземпляр элемента Spinner

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_answered_questions, container, false);

        ImageButton btn_search= (ImageButton) view.findViewById(R.id.btn_search_allQ);

        // начальная инициализация списка
        setInitialData();


        QuestionAdapter.OnStateClickListener stateClickListener = new QuestionAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(ListItemQuestion state, int position) {

                Log.d("IM TIRED", state.getQuestion() + "\n\n");

            }
        };

        // Inflate the layout for this fragment
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView_answered_questions);//
        spinner = view.findViewById(R.id.spinner_category);

        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, dayOfWeek);

        // Определяем разметку для использования при выборе элемента
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(spinnerAdapter);
        spinner.setSelection(2);

        // создаем адаптер
        QuestionAdapter adapter = new QuestionAdapter(getActivity().getApplicationContext(), questions, stateClickListener);//
        // устанавливаем для списка адаптер
        recyclerView.setAdapter(adapter);

        btn_search.setOnTouchListener(new View.OnTouchListener(){

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


        return view;
    }

    private void setInitialData(){

       // questions.add(new ListItemQuestion (1,"Любите больше кошек или собак?","С подругой спор, проголосуйте, пожалуйста :)","aaaa",1,"05.05.2023", "Животные"));
       // questions.add(new ListItemQuestion (2,"Сколько вам лет?","","aaaa",2,"05.05.2023", "Другое"));
      //  questions.add(new ListItemQuestion (3,"При жизни Вы имели большой опыт работы на ресепшне с самими разными личностями.\n" +
      //          "После смерти Вам предлагают принимать умерших, но Вы можете сами выбрать - ждать ли грешников у их вечных котлов, или же стоять на вратах в Эдемские сады.","","aaaa",1,"05.05.2023", "Другое"));
       /* questions.add(new ListItemQuestion (4,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (5,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (6,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (7,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (8,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (9,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (10,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (11,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (12,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (13,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (14,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (15,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (16,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (17,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (18,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (19,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (20,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (1,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (2,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (3,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (4,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (5,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (6,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (7,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (8,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (9,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (10,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (11,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (12,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (13,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (14,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (15,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (16,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (17,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (18,"question2","aa2","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (19,"question","aa","aaaa",1,"ssd", "category"));
        questions.add(new ListItemQuestion (20,"question2","aa2","aaaa",1,"ssd", "category"));
        */
    }
}