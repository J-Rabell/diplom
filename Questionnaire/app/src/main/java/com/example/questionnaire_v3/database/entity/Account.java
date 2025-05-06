package com.example.questionnaire_v3.database.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Account {

    @PrimaryKey
    public Integer userId;

    public String nickname;

    public String email;

    public String description;

    public String imageUrl;

}