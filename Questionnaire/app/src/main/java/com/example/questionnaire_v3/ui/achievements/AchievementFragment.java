package com.example.questionnaire_v3.ui.achievements;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionnaire_v3.R;
import com.example.questionnaire_v3.adapter.AchievementAdapter;
import com.example.questionnaire_v3.adapter.CommentAdapter;
import com.example.questionnaire_v3.data.model.ListItemAchievement;

import java.util.ArrayList;

public class AchievementFragment extends Fragment {
    private NavController navController;
    ArrayList<ListItemAchievement> achievements = new ArrayList<ListItemAchievement>();
    private Spinner spinner;

    public AchievementFragment() {
        // Required empty public constructor
    }

    public static AchievementFragment newInstance(String param1, String param2) {
        AchievementFragment fragment = new AchievementFragment();
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
        View view = inflater.inflate(R.layout.fragment_item_achievement_list, container, false);


        this.navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);

        // начальная инициализация списка
       // setInitialData();

        AchievementAdapter.OnStateClickListener stateClickListener = new AchievementAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(ListItemAchievement state, int position) {

                Log.d("IM TIRED COOMENT", state.getAchievementName() + "\n\n");

                //   navController.navigate(R.id.nav_selected_question);

            }
        };

        // Inflate the layout for this fragment
       // RecyclerView recyclerView = view.findViewById(R.id.recyclerView_achievements);//

        // создаем адаптер
       // AchievementAdapter adapter = new AchievementAdapter(getActivity().getApplicationContext(), achievements, stateClickListener);
        // устанавливаем для списка адаптер
       // recyclerView.setAdapter(adapter);

        return view;
    }

    private void setInitialData(){

        achievements.add(new ListItemAchievement(1,"Первый опрос!","url","Создайте свой первый опрос.", "05.05.2023"));
        achievements.add(new ListItemAchievement(1,"Первый комментарий!","url","Оставьте свой первый комментарий под опросом.", "05.05.2023"));


        // comments.add(new ListItemComment(1, 3, "Это комментарий5", 0, "05.05.2023", "05.05.2023", "XRabell", "qanswer", null));

    }
}
