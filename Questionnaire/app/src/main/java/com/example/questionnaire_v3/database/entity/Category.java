package com.example.questionnaire_v3.database.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {

    @PrimaryKey
    public Integer id;

    public String categoryName;

}