module.exports = class AchievementDto {
    id;
    achievementName;
    description;
    imageUrl;
    createdAt;

    constructor(model) {
        this.id = model.id;
        this.achievementName = model.achievementName;
        this.description = model.description;
        this.imageUrl = model.imageUrl;
        this.createdAt = model.users[0].user_achievement.createdAt;
    }
}
