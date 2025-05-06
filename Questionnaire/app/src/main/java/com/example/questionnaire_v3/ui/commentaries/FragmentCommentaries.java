package com.example.questionnaire_v3.ui.commentaries;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.questionnaire_v3.R;
import com.example.questionnaire_v3.adapter.CommentAdapter;
import com.example.questionnaire_v3.adapter.QuestionAdapter;
import com.example.questionnaire_v3.data.model.ListItemComment;
import com.example.questionnaire_v3.data.model.ListItemQuestion;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class FragmentCommentaries extends Fragment {

    private NavController navController;
    ArrayList<ListItemComment> comments = new ArrayList<ListItemComment>();
    private Spinner spinner;
    LinearLayout my_comment;
    TextView text;

    public FragmentCommentaries() {
        // Required empty public constructor
    }

    public static FragmentCommentaries newInstance(String param1, String param2) {
        FragmentCommentaries fragment = new FragmentCommentaries();
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
        View view = inflater.inflate(R.layout.fragment_list_comments, container, false);

        ImageButton btn_add_comment = (ImageButton) view.findViewById(R.id.btn_add_comment);
        ImageButton btn_save_comment = (ImageButton) view.findViewById(R.id.btn_save_comment);
        ImageButton btn_like1 = (ImageButton) view.findViewById(R.id.btn_like_kuro);
        ImageButton btn_dislike1 = (ImageButton) view.findViewById(R.id.btn_dislike_kuro);
        ImageButton btn_like2 = (ImageButton) view.findViewById(R.id.btn_like);
        ImageButton btn_dislike2 = (ImageButton) view.findViewById(R.id.btn_dislike);

        ImageButton btn_deleteComm = view.findViewById(R.id.btn_comment_delete1);
        ImageButton btn_edit_comment = view.findViewById(R.id.btn_edit_comment_my);

        TextView rating_comment_kuro = (TextView) view.findViewById(R.id.comment_rating);
        TextView rating_comment1 = (TextView) view.findViewById(R.id.comment_rating1);
        text = (TextView) view.findViewById(R.id.list_commentaries_comment_text);
        TextView myCommentText = view.findViewById(R.id.comment_text_my);

        LinearLayout l_achievement = view.findViewById(R.id.linear_new_achievement_comm);
        my_comment = view.findViewById(R.id.linear_my_comment);

        this.navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_content_main);

        // начальная инициализация списка
        setInitialData();

        CommentAdapter.OnStateClickListener stateClickListener = new CommentAdapter.OnStateClickListener() {
            @Override
            public void onStateClick(ListItemComment state, int position) {

                //Log.d("IM TIRED COOMENT", state.getText() + "\n\n");
             //   navController.navigate(R.id.nav_selected_question);


            }
        };

        // Inflate the layout for this fragment
        //RecyclerView recyclerView = view.findViewById(R.id.recyclerView_comments);//

        // создаем адаптер
        CommentAdapter adapter = new CommentAdapter(getActivity().getApplicationContext(), comments, stateClickListener);
        // устанавливаем для списка адаптер
        //recyclerView.setAdapter(adapter);

        btn_add_comment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new CountDownTimer(2000, 1000) { // 5000 = 5 sec
                    public void onTick(long millisUntilFinished) {
                        view.findViewById(R.id.loading_comments).setVisibility(View.VISIBLE);
                    }

                    public void onFinish() {
                        view.findViewById(R.id.loading_comments).setVisibility(View.GONE);
                        view.findViewById(R.id.linear_my_comment).setVisibility(View.VISIBLE);
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
                                        l_achievement.setVisibility(View.GONE);
                                    }
                                });
                    }
                }.start();

                text.setText("");
            }
        });

        btn_like1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(btn_like1.getAlpha() > 0.9F) {
                    btn_like1.setAlpha(0.5F);
                    rating_comment_kuro.setText("0");
                }
                else {
                    btn_like1.setAlpha(1F);
                    btn_dislike1.setAlpha(0.5F);
                    rating_comment_kuro.setText("1");
                }
            }
        });

        btn_dislike1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(btn_dislike1.getAlpha() > 0.9F) {
                    btn_dislike1.setAlpha(0.5F);
                    rating_comment_kuro.setText("0");
                }
                else {
                    btn_dislike1.setAlpha(1F);
                    btn_like1.setAlpha(0.5F);
                    rating_comment_kuro.setText("-1");
                }
            }
        });

        btn_deleteComm.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showAlertDialog_DeleteComment("Вы действительно хотите удалить комментарий?");
            }
        });

        btn_edit_comment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                text.setText("Мне нравятся кошки своим своенравием");
                text.setFocusable(true);
                btn_add_comment.setVisibility(View.GONE);
                btn_save_comment.setVisibility(View.VISIBLE);
            }
        });

        btn_save_comment.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                text.setText("");
                btn_add_comment.setVisibility(View.VISIBLE);
                btn_save_comment.setVisibility(View.GONE);
                new CountDownTimer(2000, 1000) { // 5000 = 5 sec
                    public void onTick(long millisUntilFinished) {
                        view.findViewById(R.id.loading_comments).setVisibility(View.VISIBLE);
                    }

                    public void onFinish() {
                        view.findViewById(R.id.loading_comments).setVisibility(View.GONE);
                        view.findViewById(R.id.linear_my_comment).setVisibility(View.VISIBLE);

                        myCommentText.setText("Мне нравятся кошки своим своенравием, а еще они милые");
                    }
                }.start();

            }
        });



        return view;
    }
    public void showAlertDialog_DeleteComment(String text) {
        // setup the alert builder
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage(text);
        // add the buttons
        builder.setPositiveButton("Да", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                my_comment.setVisibility(View.GONE);
                // deleteAccount();
            }
        });
        builder.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                // deleteAccount();
            }
        });
        // create and show the alert dialog
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    private void setInitialData(){

        // comments.add(new ListItemComment(1, 1, "Кошки более своевольные, чем мне они и нравятся", 0, "06.05.2023", "05.05.2023", "XRabell", "Кошек", null));
        comments.add(new ListItemComment(1, 2, "Кошки", 0, "05.05.2023", "05.05.2023", "Куро", "Кошек", null));
       // comments.add(new ListItemComment(1, 3, "Это комментарий5", 0, "05.05.2023", "05.05.2023", "XRabell", "qanswer", null));


    }
}