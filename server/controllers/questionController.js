const {Question, Answer, Category, User_answer, Account, Rating_question, User} = require('../models/models')
const ApiError = require('../error/ApiError');
const sequelize = require('../db')
const { Op } = require("sequelize");
const uuid = require('uuid')
const path = require('path')
const fs = require("fs");
const QuestionDto = require('../dtos/question-dto');
const ListQuestionDto = require('../dtos/listQuestion-dto');
const CountVoteDto = require('../dtos/countVote-dto');
const { Console } = require('console');


class QuestionController {
    async create(req, res, next) {
      
        try {
            let {userId, categoryId, question, description, answers} = req.body
            const ans = JSON.parse(answers)
            let newQuestion = null
            if(req.files)
            { 
                const {img} = req.files
                let fileName = uuid.v4() + ".jpg"
                try{
                const result = await sequelize.transaction(async (t) => {

                newQuestion = await Question.create({userId, categoryId, question, description, imageUrl: fileName});
                   
                if (ans) {
                    for (let index = 0; index < ans.length; ++index) {
                        await Answer.create({//await?
                            answer: ans[index],
                            questionId: newQuestion.id
                        })  
                    }
                }
                }); 
                
                img.mv(path.resolve(__dirname, '..', 'static\/question_img', fileName))
                return res.json(newQuestion)
                }
                catch(err){
                    return next(ApiError.badRequest(err))
                }
            }
            else{
                const result = await sequelize.transaction(async (t) => {

                newQuestion = await Question.create({userId, categoryId, question, description});
                   
                if (ans) {
                    for (let index = 0; index < ans.length; ++index) {
                        await Answer.create({//await?
                            answer: ans[index],
                            questionId: newQuestion.id
                        })  
                    }
                }
            });

            return res.json(newQuestion) 
        }  
        } catch (e) {
           return next(ApiError.badRequest(e.message))
        }
    }

    async vote(req, res, next){
        try{
        const {answerId, userId, answers} = req.body //answers - все варианты ответов
        const userAns =  await User_answer.create({userId, answerId})
        let countVotes = [CountVoteDto]

        for(let i = 0; i<answers.length; i++)
        {
            let count = await User_answer.count({where:{answerId: answers[i]}})
            countVotes[i] = new CountVoteDto(answers[i], count)
        }

        return res.json(countVotes)
       }
        catch(e){
            return next(ApiError.badRequest(e.message))
        }
    }

    async deleteVote(req, res, next){// при зажатии элемента удаляем голос
        try{
        const {answerId, userId} = req.body
        const userAns =  await User_answer.destroy({where: {answerId, userId}})

        return res.json(userAns)
       }
        catch(e){
            return next(ApiError.badRequest(e.message))
        }
    }

    async update(req, res, next) {//description, imageUrl, category
        try{
            const {id} = req.params
            const {categoryId, description, imgDel} = req.body
            if(!id)
            {
                return next(ApiError.badRequest('Не задан ID'))
            }
            if(categoryId)
            {
                await Question.update({categoryId}, {
                    where: {id}
                  })
            }
            if(description)
            {
                await Question.update({description}, {
                    where: {id}
                  })
            }
            if(req.files)
            { 
                const {img} = req.files
                const {imageUrl} = await Question.findOne(
                {
                    where: {id}
                },
                )

                if(imageUrl!=null){
                try {
                    fs.unlinkSync(path.resolve(__dirname, '..', 'static\/question_img', imageUrl))
                    console.log("Successfully deleted the file.")
                  } catch(err) {
                    ApiError.internal('Не удалось удалить файл')
                  }
                }
    
                try{

                let fileName = uuid.v4() + ".jpg"

                await Question.update({imageUrl: fileName }, {
                    where: {id}
                  })
               
                  img.mv(path.resolve(__dirname, '..', 'static\/question_img', fileName))
                }
                catch(err){
                    ApiError.badRequest(err)
                }
            }
            if(imgDel){
                const {imageUrl} = await Question.findOne(
                    {
                        where: {id}
                    },
                )

                if(imageUrl!=null){
                    try {
                        fs.unlinkSync(path.resolve(__dirname, '..', 'static\/question_img', imageUrl))
                        console.log("Successfully deleted the file.")
                      } catch(err) {
                        ApiError.internal('Не удалось удалить файл')
                      }
                }
                await Question.update({imageUrl: null}, {
                    where: {id}
                })    
            }


            const q = await Question.findOne(
                {
                    where: {id}
                },)
            return res.json(q)
            }
            catch(err){ 
                next(ApiError.badRequest(err.message))
            }
    }

    async getAll(req, res, next) {
        try{
        let {categoryId, searchStr, limit, page} = newFunction()
        page = page || 1
        limit = limit || 9
        let offset = page * limit - limit

        let questionsData = [ListQuestionDto];
        let questions = null

        if (!categoryId && !searchStr) {
            questions = await Question.findAndCountAll({include:{model:Category}, limit, offset,  order: [
                ['id', 'DESC'],
            ],})
        }
        if (categoryId && !searchStr) {
            questions = await Question.findAndCountAll({where:{categoryId}, include:{model:Category}, limit, offset,  order: [
                ['id', 'DESC'],
            ],})
        }
        if (!categoryId && searchStr) {
            questions = await Question.findAndCountAll({ 
                where: {
                question: {
                  [Op.iLike]: '%'+searchStr+'%'
                }
              }, include:{model:Category},  limit, offset,  order: [
                ['id', 'DESC'],
            ],})
        }
        if (categoryId && searchStr) {
            questions = await Question.findAndCountAll({
                where:{
                categoryId, 
                question: {
                    [Op.iLike]: '%'+searchStr+'%'
                  }
                }, include:{model:Category}, limit, offset,  order: [
                    ['id', 'DESC'],
                ],})
        }

        for(let i = 0; i<questions.rows.length; i++){
            questionsData[i] = new ListQuestionDto(questions.rows[i])
        }

        return res.json(questionsData)
        }
        catch(e){
            next(ApiError.internal(e.message))
        }

        function newFunction() {
            return req.query;
        }
    }

    async getOne(req, res, next) {
        const {id} = req.params
        const {userId} = req.query

        if(!id)
        {
            return next(ApiError.badRequest('Не задан ID'))
        }

        try{
        const question = await Question.findOne(
            {
                where: {id},
                include: [{model: Answer, as: 'answers'}, {model: Category}]

            },
        )

        const questionData = new QuestionDto(question)

        if(question.userId!=null){
        const {nickname} = await Account.findOne({
            where: {userId: question.userId}
        })
        questionData.nickname = nickname
       }


        let selectAnswer = null
        let countVote = [CountVoteDto]

        for(let i = 0; i<questionData.answers.length; i++){

            let answerId = questionData.answers[i].id
            if(!selectAnswer){
            selectAnswer = await User_answer.findOne({
                where: {userId: userId, answerId}
            })
            }

            let count = await User_answer.count({
                where: {answerId}
            })

            countVote[i] = new CountVoteDto(answerId, count) 
        }

        questionData.selectAnswer = selectAnswer
        questionData.answersVoteCount = countVote

        if(questionData.userId == userId){
            questionData.isCreator = true
        }
        else {
            questionData.isCreator = false
        }

        let questionRate = await Rating_question.findOne({where:{userId, questionId: id}})

        if(questionRate){
            questionData.isLike = questionRate.questionRate
        }
        else {
            questionData.isLike = null
        }

        return res.json(questionData)
        }
        catch(e){
            next(ApiError.badRequest(e.message))
        }
     }

   /* async getOne(req, res, next) {
        const {id} = req.params
        const {userId} = req.query

        if(!id)
        {
            return next(ApiError.badRequest('Не задан ID'))
        }

        try{
        const question = await Question.findOne(
            {
                where: {id},
                include: [
                  
                    {
                        model: Answer, as: 'answers',
                        include: {model: User, 
                            attributes: [[sequelize.fn('count', sequelize.col('user.id')), 'countVote']],
                            where: {id: userId},
                            required:false,
                           // group: "user.id"
                        }
                    }, 
                    //group: ["question.id"]
                ],
             //   group:["question.id"]

            },
        )*/

    /*    const {nickname} = await Account.findOne({
            where: {userId: question.userId}
        })

        const questionData = new QuestionDto(question)
        questionData.nickname = nickname

        let selectAnswer = null
        let countVote = [CountVoteDto]

        for(let i = 0; i<questionData.answers.length; i++){

            let answerId = questionData.answers[i].id
            if(!selectAnswer){
            selectAnswer = await User_answer.findOne({
                where: {userId: userId, answerId}
            })
            }

            let count = await User_answer.count({
                where: {answerId}
            })

            countVote[i] = new CountVoteDto(answerId, count) 
        }

        questionData.selectAnswer = selectAnswer
        questionData.answersVoteCount = countVote

        if(questionData.userId == userId){
            questionData.isCreator = true
        }
        else {
            questionData.isCreator = false
        }

        let questionRate = await Rating_question.findOne({where:{userId, questionId: id}})

        if(questionRate){
            questionData.isLike = questionRate.questionRate
        }
        else {
            questionData.isLike = null
        }

        return res.json(question)
        }
        catch(e){
            next(ApiError.badRequest(e.message))
        }
     }*/

    async deleteOne(req, res, next) {
        try{
            const {id} = req.params
            if(!id)
            {
                return next(ApiError.badRequest('Не задан ID'))
            }

            const {imageUrl} = await Question.findOne({
                where: {id: id}
            })
            const q = await Question.destroy({
                where: {id}
              })
    
              if(imageUrl!=null){
                try {
                    fs.unlinkSync(path.resolve(__dirname, '..', 'static\/question_img', imageUrl))
                    console.log("Successfully deleted the file.")
                   
                  } catch(err) {
                    ApiError.internal('Не удалось удалить файл')
                  
                }
            }
            return res.json(q)
            }
            catch(e){
                next(ApiError.badRequest(e.message))
            }
    }

}

module.exports = new QuestionController()
