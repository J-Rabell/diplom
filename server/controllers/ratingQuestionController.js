const {Rating_question, Question} = require('../models/models')
const ApiError = require('../error/ApiError');
const sequelize = require('../db')
/*
Рейтинг вопросов...
допустим если это все разделять на телефоне, а не на сервере, то получается получаем при открытии вопроса сам вопрос и рейтинг его, 
а так же данные нажимал пользователь на лайк или дизлайк
если не нажимал ниего, то при нажатии на лайк или дизлайк один запрос, если нажимал, то другой
мобильное приложение это определяет, а не сервер... сервер лишь получает какие данные обновить или выслать в ответ на нажатие?
*/
class RatingQuestionController {
    async addRate(req, res, next) {

        try{
        const{questionId, userId, rate} = req.body
        let qrate = null
        let q = null
        const result = await sequelize.transaction(async (t) => {

        await Rating_question.create({userId, questionId, questionRate: rate})

        qrate = await Rating_question.findAll({
            attributes: [[sequelize.fn('sum', sequelize.col('questionRate')), 'rating']],
            where: {questionId: questionId},
            raw: true
          });
          
          if(qrate[0].rating==null) qrate[0].rating = 0

          await Question.update({rating: qrate[0].rating}, {
            where: {id: questionId}
          })

        }); 

        return res.json(qrate)
        }
        catch(e){
            return next(ApiError.badRequest(e.message))
        }
    }

    async updateRate(req, res, next) {
    try{
        const{questionId, userId, rate} = req.body
        let qrate = null
        const result = await sequelize.transaction(async (t) => {

        await Rating_question.update({questionRate: rate}, 
        {
            where: {questionId, userId}
        })
        qrate = await Rating_question.findAll({
            attributes: [[sequelize.fn('sum', sequelize.col('questionRate')), 'rating']],
            where: {questionId: questionId},
            raw: true
          });
        
        if(qrate[0].rating==null) qrate[0].rating = 0

        await Question.update({rating: qrate[0].rating}, {where:{id:questionId}})
          
        }); 
        return res.json(qrate)
        }
        catch(e){
            return next(ApiError.badRequest(e.message))
        }
    }

    async getRate(req, res, next) {

        return res.json('ratingAddRate')
    }

    async deleteRate(req, res, next) {
        try{
            const{questionId, userId} = req.body

            let qrate = null
            const result = await sequelize.transaction(async (t) => {
    
            const del = await Rating_question.destroy(
            {
                where: {questionId, userId}
            })

            qrate = await Rating_question.findAll({
                attributes: [[sequelize.fn('sum', sequelize.col('questionRate')), 'rating']],
                where: {questionId: questionId},
                raw: true
              });
            
            if(qrate[0].rating==null) qrate[0].rating = 0
            
            await Question.update({rating: qrate[0].rating}, {where:{id:questionId}})
              
            }); 
            return res.json(qrate)
            }
            catch(e){
                return next(ApiError.badRequest(e.message))
            }
    }
 
}

module.exports = new RatingQuestionController()
