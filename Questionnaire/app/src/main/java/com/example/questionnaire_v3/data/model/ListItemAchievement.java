package com.example.questionnaire_v3.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListItemAchievement {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("achievementName")
    @Expose
    private String achievementName;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    public ListItemAchievement() {
    }

    public ListItemAchievement(Integer id, String achievementName, String imageUrl, String description, String createdAt) {
        super();
        this.id = id;
        this.achievementName = achievementName;
        this.imageUrl = imageUrl;
        this.description = description;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAchievementName() {
        return achievementName;
    }

    public void setAchievementName(String achievementName) {
        this.achievementName = achievementName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}