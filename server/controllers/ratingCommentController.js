const {Rating_comment, Comment} = require('../models/models')
const ApiError = require('../error/ApiError');
const sequelize = require('../db')
/*
Рейтинг вопросов...
допустим если это все разделять на телефоне, а не на сервере, то получается получаем при открытии вопроса сам вопрос и рейтинг его, 
а так же данные нажимал пользователь на лайк или дизлайк
если не нажимал ниего, то при нажатии на лайк или дизлайк один запрос, если нажимал, то другой
мобильное приложение это определяет, а не сервер... сервер лишь получает какие данные обновить или выслать в ответ на нажатие?
*/
class RatingCommentController {
    async addRate(req, res, next) {

        try{
        const{commentId, userId, rate} = req.body
        let crate = null
        const result = await sequelize.transaction(async (t) => {

        await Rating_comment.create({userId, commentId, commentRate: rate})

        crate = await Rating_comment.findAll({
            attributes: [[sequelize.fn('sum', sequelize.col('commentRate')), 'rating']],
            where: {commentId: commentId},
            raw: true
          });
          
          if(crate[0].rating==null) crate[0].rating = 0

          await Comment.update({rating: crate[0].rating}, {
            where: {id: commentId}
          })

        }); 

        return res.json(crate)
        }
        catch(e){
            return next(ApiError.badRequest(e.message))
        }
    }

    async updateRate(req, res, next) {
    try{
        const{commentId, userId, rate} = req.body
        let crate = null
        const result = await sequelize.transaction(async (t) => {

        await Rating_comment.update({commentRate: rate}, {where: {commentId, userId}})

        crate = await Rating_comment.findAll({
            attributes: [[sequelize.fn('sum', sequelize.col('commentRate')), 'rating']],
            where: {commentId: commentId},
            raw: true
          });
          
          if(crate[0].rating==null) crate[0].rating = 0

          await Comment.update({rating: crate[0].rating}, {
            where: {id: commentId}
          })

        }); 

        return res.json(crate)
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
            const{commentId, userId} = req.body

            let crate = null
            const result = await sequelize.transaction(async (t) => {
    
            await Rating_comment.destroy({where: {userId, commentId}})
    
            crate = await Rating_comment.findAll({
                attributes: [[sequelize.fn('sum', sequelize.col('commentRate')), 'rating']],
                where: {commentId: commentId},
                raw: true
              });
              
              if(crate[0].rating==null) crate[0].rating = 0
    
              await Comment.update({rating: crate[0].rating}, {
                where: {id: commentId}
              })
    
            }); 
    
            return res.json(crate)
            }
            catch(e){
                return next(ApiError.badRequest(e.message))
            }
    }
 
}

module.exports = new RatingCommentController()
