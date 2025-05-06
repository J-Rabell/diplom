package com.example.questionnaire_v3.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.questionnaire_v3.database.dao.AccountDao;
import com.example.questionnaire_v3.database.dao.AchievementDao;
import com.example.questionnaire_v3.database.dao.CategoryDao;
import com.example.questionnaire_v3.database.entity.Account;
import com.example.questionnaire_v3.database.entity.Achievement;
import com.example.questionnaire_v3.database.entity.Category;
import com.example.questionnaire_v3.database.entity.UserAchievement;

@Database(entities = {Account.class, Category.class, Achievement.class, UserAchievement.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AccountDao accountDao();
    public abstract CategoryDao categoryDao();
    public abstract AchievementDao achievementDao();

}