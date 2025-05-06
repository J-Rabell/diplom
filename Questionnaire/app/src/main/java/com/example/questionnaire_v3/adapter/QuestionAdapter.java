package com.example.questionnaire_v3.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.questionnaire_v3.R;
import com.example.questionnaire_v3.data.model.ListItemQuestion;

import java.util.List;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{
    public interface OnStateClickListener{
        void onStateClick(ListItemQuestion question, int position);
    }

    private final OnStateClickListener onClickListener;
    private final LayoutInflater inflater;
    private List<ListItemQuestion> questions;

    public QuestionAdapter(Context context, List<ListItemQuestion> listItemQuestions, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.questions = listItemQuestions;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item_question, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(QuestionAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ListItemQuestion question = questions.get(position);
        holder.nameQuestion.setText(question.getQuestion());
        holder.description.setText(question.getDescription());
        holder.date.setText(question.getCreatedAt());
        holder.rating.setText(question.getRating().toString());
        holder.category.setText(question.getCategory());
        holder.image.setImageResource(R.drawable.cat);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(question, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameQuestion, description, rating, date, category;
        final ImageView image;
        ViewHolder(View view){
            super(view);
            nameQuestion = view.findViewById(R.id.item_name_question);
            description = view.findViewById(R.id.item_description_question);
            rating = view.findViewById(R.id.item_rating);
            date = view.findViewById(R.id.item_created_date);
            category = view.findViewById(R.id.item_category);
            image = view.findViewById(R.id.roundedImageView);
        }
    }

    public void setList(List<ListItemQuestion> listItemQuestions) {
        this.questions = listItemQuestions;
        notifyDataSetChanged();
    }
}