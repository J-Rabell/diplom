package com.example.questionnaire_v3.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.questionnaire_v3.R;
import com.example.questionnaire_v3.data.model.ListItemComment;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    public interface OnStateClickListener{
        void onStateClick(ListItemComment question, int position);
    }

    private final OnStateClickListener onClickListener;
    private final LayoutInflater inflater;
    private List<ListItemComment> comments;

    public CommentAdapter(Context context, List<ListItemComment> listItemComments, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.comments = listItemComments;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.list_item_comment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CommentAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ListItemComment comment = comments.get(position);
        holder.nickname.setText(comment.getNickname());
        holder.date.setText(comment.getCreatedAt());
        holder.answer.setText(comment.getSelectAnswer());
        holder.comment.setText(comment.getText());
        holder.rating.setText(comment.getRating().toString());
        if(comment.getUserId() == 1)//userId
        {
            holder.btn_comment_delete.setVisibility(View.VISIBLE);
        }
        else {
            holder.btn_comment_delete.setVisibility(View.INVISIBLE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(comment, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nickname, answer, date, comment, rating;

        final ImageButton btn_add_comment;
        final ImageButton btn_comment_delete;
        ViewHolder(View view){
            super(view);
            nickname = view.findViewById(R.id.comment_nickname);
            answer = view.findViewById(R.id.comment_answer);
            rating = view.findViewById(R.id.comment_rating);
            date = view.findViewById(R.id.comment_date);
            comment = view.findViewById(R.id.comment_text);
            btn_add_comment = view.findViewById(R.id.btn_add_comment);
            btn_comment_delete = view.findViewById(R.id.btn_comment_delete);
        }
    }

    public void setList(List<ListItemComment> listItemComments) {
        this.comments = listItemComments;
        notifyDataSetChanged();
    }
}