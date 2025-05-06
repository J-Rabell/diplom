const sequelize = require('../db')
const {DataTypes} = require('sequelize')
const queryInterface = sequelize.getQueryInterface()

const Role = sequelize.define('role', {//1
    id: {type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true},
    roleName: {type: DataTypes.STRING(20), unique: true, allowNull: false},
}, { 
    timestamps: false
})

const Role_user = sequelize.define('role_user', {//2
    id: {type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true},
}, { 
    timestamps: false
})

const Category = sequelize.define('category', {//3
    id: {type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true},
    categoryName: {type: DataTypes.STRING, unique: true, allowNull: false},
}, { 
    timestamps: false
})

const Account = sequelize.define('account', {//4
    id: {type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true},
    nickname: {type: DataTypes.STRING(20), allowNull: false},
    description: {type: DataTypes.STRING(255)},//update??? 255
    imageUrl: {type: DataTypes.STRING},
}, { 
    timestamps: false
})

const User = sequelize.define('user', {//5
    id: {type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true},
    email: {type: DataTypes.STRING, unique: true, allowNull: false},
    password: {type: DataTypes.STRING, allowNull: false},
    isActivated: {type: DataTypes.BOOLEAN, defaultValue: false},
    activationLink: {type: DataTypes.STRING} 
}, { 
    timestamps: false
})

const Token = sequelize.define('token', {//new
    refreshToken: {type: DataTypes.STRING, allowNull: false},
}, { 
    timestamps: false
})

const User_achievement = sequelize.define('user_achievement', {//6
    id: {type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true}, 
    createdAt: {type: DataTypes.DATE, allowNull: false}    
}, { 
    timestamps: false
})

const Achievement = sequelize.define('achievement', {//7
    id: {type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true},
    achievementName: {type: DataTypes.STRING, unique: true, allowNull: false},
    imageUrl: {type: DataTypes.STRING, allowNull: false},
    description: {type: DataTypes.STRING, allowNull: false},
}, { 
    timestamps: false
})

const Question = sequelize.define('question', {//8
    id: {type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true},
    question:  {type: DataTypes.STRING, allowNull: false},
    description: {type: DataTypes.STRING},
    imageUrl: {type: DataTypes.STRING},
    rating: {type: DataTypes.INTEGER, defaultValue: 0},
})

const Answer = sequelize.define('answer', {//9
    id: {type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true},
    answer: {type: DataTypes.STRING, allowNull: false},
}, { 
    timestamps: false
})

const User_answer = sequelize.define('user_answer', {//10
    id: {type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true},
})//обновить бд, удалить timestamps и добавить updatedAt: false для статистики

const Rating_question = sequelize.define('rating_question', {//11
    id: {type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true},
    questionRate: {type: DataTypes.INTEGER, allowNull: false},
}, { 
    timestamps: false
})

const Comment = sequelize.define('comment', {//12
    id: {type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true},
    text: {type: DataTypes.STRING, allowNull: false},
    rating: {type: DataTypes.INTEGER, defaultValue: 0}
})

const Rating_comment = sequelize.define('rating_comment', {//13
    id: {type: DataTypes.INTEGER, primaryKey: true, autoIncrement: true},
    commentRate: {type: DataTypes.INTEGER, allowNull: false},
}, { 
    timestamps: false
})

// связи таблиц

Role.belongsToMany(User, {through: Role_user, onDelete: 'SET NULL'})
User.belongsToMany(Role, {through: Role_user, onDelete: 'SET NULL'})

User.hasOne(Account, {onDelete: 'CASCADE'})
Account.belongsTo(User)

User.belongsToMany(Achievement, {through: User_achievement, onDelete: 'SET NULL'})
Achievement.belongsToMany(User, {through: User_achievement, onDelete: 'CASCADE'})

User.hasMany(Comment, {onDelete: 'CASCADE'})
Comment.belongsTo(User)

User.hasMany(Rating_comment, {onDelete: 'CASCADE'})
Rating_comment.belongsTo(User)

User.hasMany(Question, {onDelete: 'SET NULL'})//UPDATE
Question.belongsTo(User)

User.belongsToMany(Answer, {through: User_answer, onDelete: 'CASCADE'})
Answer.belongsToMany(User, {through: User_answer, onDelete: 'CASCADE'})

User.hasMany(Rating_question, {onDelete: 'CASCADE'})
Rating_question.belongsTo(User)

User.hasOne(Token, {onDelete: 'CASCADE'})
Token.belongsTo(User)

Question.hasMany(Comment, {onDelete: 'CASCADE'})
Comment.belongsTo(Question)

Question.hasMany(Rating_question, {onDelete: 'CASCADE'})
Rating_question.belongsTo(Question)

Question.hasMany(Answer, {onDelete: 'CASCADE'})
Answer.belongsTo(Question)

Category.hasMany(Question, {onDelete: 'RESTRICT'})
Question.belongsTo(Category)

Comment.hasMany(Rating_comment, {onDelete: 'CASCADE'})
Rating_comment.belongsTo(Comment)


// уникальные пары значений
/*
queryInterface.addConstraint('rating_questions', {
    fields: ['userId', 'questionId'],
    type: 'unique',
    name: 'unique_constraint_rating_question'
  });


queryInterface.addConstraint('rating_comments', {
    fields: ['userId', 'commentId'],
    type: 'unique',
    name: 'unique_constraint_rating_comment'
  });


queryInterface.addConstraint('comments', {
    fields: ['userId', 'questionId'],
    type: 'unique',
    name: 'unique_constraint_comment'
  });*/

  module.exports = {
    Role,
    Role_user, 
    Category,
    Account,
    User,
    User_achievement,
    Achievement,
    Question,
    Answer,
    User_answer,
    Rating_question,
    Comment,
    Rating_comment,
    Token
}