package com.example.questionnaire_v3.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.questionnaire_v3.database.entity.Achievement;
import com.example.questionnaire_v3.database.entity.UserAchievement;

import java.util.List;

@Dao
public interface AchievementDao {

    @Query("SELECT * FROM achievement")
    List<Achievement> getAll();

    @Query("SELECT achievement.id, achievement.achievementName, achievement.description, achievement.imageUrl" +
            " FROM achievement join userAchievement on achievement.id == userAchievement.achievementId" +
            " where userAchievement.userId == :userId")
    List<Achievement> getAllByUser(long userId);

    @Insert
    void insert(Achievement achievement);

    @Insert
    void insert(UserAchievement userAchievement);
}