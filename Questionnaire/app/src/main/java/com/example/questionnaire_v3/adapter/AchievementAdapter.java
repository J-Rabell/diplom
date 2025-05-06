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
import com.example.questionnaire_v3.data.model.ListItemAchievement;

import org.w3c.dom.Text;

import java.util.List;

public class AchievementAdapter extends RecyclerView.Adapter<AchievementAdapter.ViewHolder>{
    public interface OnStateClickListener{
        void onStateClick(ListItemAchievement achievement, int position);
    }

    private final OnStateClickListener onClickListener;
    private final LayoutInflater inflater;
    private List<ListItemAchievement> achievements;

    public AchievementAdapter(Context context, List<ListItemAchievement> listItemAchievements, OnStateClickListener onClickListener) {
        this.onClickListener = onClickListener;
        this.achievements = listItemAchievements;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public AchievementAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.fragment_item_achievement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AchievementAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ListItemAchievement achievement = achievements.get(position);

        holder.title.setText(achievement.getAchievementName());
        holder.description.setText(achievement.getDescription());
        holder.date.setText(achievement.getCreatedAt());

        //  holder.description.setText(achievement.getDescription());
       // holder.date.setText(achievement.getCreatedAt());
        //holder.rating.setText(achievement.getRating().toString());
        // holder.category.setText(achievement.getCategory());
        holder.imageView.setImageResource(R.drawable.ic_menu_my_question);

        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                onClickListener.onStateClick(achievement, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return achievements.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView title, description, date;
        ViewHolder(View view){
            super(view);
        imageView = (ImageView) view.findViewById(R.id.achievement_icon);
        title = (TextView) view.findViewById(R.id.achievement_title);
        description = (TextView) view.findViewById(R.id.achievement_description);
        date = (TextView) view.findViewById(R.id.achievement_date);
        }
    }

    public void setList(List<ListItemAchievement> listItemQuestions) {
        this.achievements = listItemQuestions;
        notifyDataSetChanged();
    }
}