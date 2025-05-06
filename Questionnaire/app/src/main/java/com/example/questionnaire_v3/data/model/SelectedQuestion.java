package com.example.questionnaire_v3.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

class Answer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("answer")
    @Expose
    private String answer;
    @SerializedName("questionId")
    @Expose
    private Integer questionId;

    /**
     * No args constructor for use in serialization
     *
     */
    public Answer() {
    }

    public Answer(Integer id, String answer, Integer questionId) {
        super();
        this.id = id;
        this.answer = answer;
        this.questionId = questionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

}

class AnswersVoteCount {

    @SerializedName("answerId")
    @Expose
    private Integer answerId;
    @SerializedName("countVote")
    @Expose
    private Integer countVote;

    /**
     * No args constructor for use in serialization
     *
     */
    public AnswersVoteCount() {
    }

    public AnswersVoteCount(Integer answerId, Integer countVote) {
        super();
        this.answerId = answerId;
        this.countVote = countVote;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public Integer getCountVote() {
        return countVote;
    }

    public void setCountVote(Integer countVote) {
        this.countVote = countVote;
    }

}

public class SelectedQuestion {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("userId")
    @Expose
    private Integer userId;
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("imageUrl")
    @Expose
    private String imageUrl;
    @SerializedName("rating")
    @Expose
    private Integer rating;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("nickname")
    @Expose
    private String nickname;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("answers")
    @Expose
    private List<Answer> answers;
    @SerializedName("answersVoteCount")
    @Expose
    private List<AnswersVoteCount> answersVoteCount;
    @SerializedName("selectAnswer")
    @Expose
    private Object selectAnswer;
    @SerializedName("isCreator")
    @Expose
    private Boolean isCreator;
    @SerializedName("isLike")
    @Expose
    private Object isLike;

    /**
     * No args constructor for use in serialization
     *
     */
    public SelectedQuestion() {
    }


    public SelectedQuestion(Integer id, Integer userId, String question, String description, String imageUrl, Integer rating, String createdAt, String nickname, String category, List<Answer> answers, List<AnswersVoteCount> answersVoteCount, Object selectAnswer, Boolean isCreator, Object isLike) {
        super();
        this.id = id;
        this.userId = userId;
        this.question = question;
        this.description = description;
        this.imageUrl = imageUrl;
        this.rating = rating;
        this.createdAt = createdAt;
        this.nickname = nickname;
        this.category = category;
        this.answers = answers;
        this.answersVoteCount = answersVoteCount;
        this.selectAnswer = selectAnswer;
        this.isCreator = isCreator;
        this.isLike = isLike;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<AnswersVoteCount> getAnswersVoteCount() {
        return answersVoteCount;
    }

    public void setAnswersVoteCount(List<AnswersVoteCount> answersVoteCount) {
        this.answersVoteCount = answersVoteCount;
    }

    public Object getSelectAnswer() {
        return selectAnswer;
    }

    public void setSelectAnswer(Object selectAnswer) {
        this.selectAnswer = selectAnswer;
    }

    public Boolean getIsCreator() {
        return isCreator;
    }

    public void setIsCreator(Boolean isCreator) {
        this.isCreator = isCreator;
    }

    public Object getIsLike() {
        return isLike;
    }

    public void setIsLike(Object isLike) {
        this.isLike = isLike;
    }

}