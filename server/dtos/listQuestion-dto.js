module.exports = class ListQuestionDto {
    id;
    question;
    description;
    imageUrl;
    rating;
    createdAt;
    category;
    
    constructor(model) {
        this.id = model.id;
        this.question = model.question;
        this.description = model.description;
        this.imageUrl = model.imageUrl;
        this.rating = model.rating;
        this.createdAt = model.createdAt;
        this.category = model.category.categoryName;
        
    }
}

