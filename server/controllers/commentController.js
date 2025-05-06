const {Comment, Account, User, Rating_comment, Answer} = require('../models/models')
const ApiError = require('../error/ApiError');
const CommentDto = require('../dtos/comment-dto');

class CommentController {
    async create(req, res, next) {
        try {
            let {userId, questionId, text} = req.body
            const comment = await Comment.create({userId, questionId, text})
            return res.json(comment)
        } catch (e) {
            next(ApiError.badRequest(e.message))
        }
    }

    async update(req, res, next) {
        try {
            let {id} = req.params
            let {text} = req.body
            const comment = await Comment.update({text}, {
                where: {id}
              })
            return res.json(comment)
        } catch (e) {
            next(ApiError.badRequest(e.message))
        }
     }
 

    async getAll(req, res, next) {
        try{
            const allComments = await Comment.findAll()
            return res.json(allComments)
        }
        catch(e){
            next(ApiError.internal(e.message))
        }

    }
    async getAllByQuestion(req, res, next) {//также сделать так, чтобы было видно, за какоц вариант проголосовал юзер, оставивший коммент
        try {
            let {questionId} = req.params
            let {limit, page, userId} = req.query
            page = page || 1
            limit = limit || 9
            let offset = page * limit - limit
            let commentsData = [CommentDto];

            const questionComments = await Comment.findAndCountAll( {
                where: {questionId},
                include: [{
                    model: Rating_comment, 
                    required: false, 
                    where: {userId: userId},
                },
                {
                    model: User,
                    include: [{
                        model: Account,
                        required: false
                    }]
                },
                {
                    model: User,
                    required: false,
                    include: [{
                        model: Answer,
                        required: false,
                    }]
                }

            ],
                limit,
                offset
            })

            if(questionComments.count > 0){
                for(let i = 0; i<questionComments.rows.length; i++){
                    commentsData[i] = new CommentDto(questionComments.rows[i])
                }
            }

            return res.json(commentsData)
        } catch (e) {
            next(ApiError.badRequest(e.message))
        }
    }
}

module.exports = new CommentController()
