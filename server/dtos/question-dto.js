module.exports = class QuestionDto {
    id;
    userId;
    question;
    description;
    imageUrl;
    rating;
    createdAt;
    nickname;
    category;
    answers;
    answersVoteCount;
    selectAnswer;
    isCreator;
    isLike; // if not - null, if true - 1, if false - -1

    constructor(model) {
        this.id = model.id;
        this.userId = model.userId;
        this.question = model.question;
        this.description = model.description;
        this.imageUrl = model.imageUrl;
        this.rating = model.rating;
        this.createdAt = model.createdAt;
        this.category = model.category.categoryName;
        this.answers = model.answers;       
        this.nickname = "УДАЛЕН" 
    }
}

