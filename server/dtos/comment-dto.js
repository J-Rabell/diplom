module.exports = class CommentDto {
    id;
    userId;
    text;
    rating;
    createdAt;
    updatedAt;
    nickname;
    selectAnswer;
    isLike; // if not - null, if true - 1, if false - -1
    
    constructor(model) {
        this.id = model.id;
        this.userId = model.userId;
        this.text = model.text;
        this.rating = model.rating;
        this.createdAt = model.createdAt;
        this.updatedAt = model.updatedAt;
        this.nickname = model.user.account.nickname;
        if(model.user.answers.length>0){
        this.selectAnswer = model.user.answers[0].answer;
        }
        else{
            this.selectAnswer = null
        }
        if(model.rating_comments.length>0){
        this.isLike = model.rating_comments[0].commentRate
        }
        else{
        this.isLike = null
        }

    }
}

