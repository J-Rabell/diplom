package com.example.questionnaire_v3.database.entity;


import static androidx.room.ForeignKey.CASCADE;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "userAchievement",
        foreignKeys = {
        @ForeignKey(entity = Account.class, parentColumns = "userId", childColumns = "userId", onDelete = CASCADE),
        @ForeignKey(entity = Achievement.class, parentColumns = "id", childColumns = "achievementId", onDelete = CASCADE)}
)
public class UserAchievement {
    @PrimaryKey
    public Integer id;

    public String createdAt;
    @ColumnInfo(name = "userId")
    public Integer userId;
    @ColumnInfo(name = "achievementId")
    public Integer achievementId;
}
