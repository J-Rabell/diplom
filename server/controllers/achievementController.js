const uuid = require('uuid')
const path = require('path');
const {Achievement, User_achievement, User} = require('../models/models')
const ApiError = require('../error/ApiError');
const fs = require("fs");
const AchievementDto = require('../dtos/achievement-dto');

class AchievementController {
    async create(req, res, next) {
        try {
            let {achievementName, description} = req.body
            const {img} = req.files
            
            let fileName = uuid.v4() + ".jpg"

            const achie = await Achievement.findOne({
                where: { 
                    achievementName: achievementName
                }
            })
            if(achie){
                throw ApiError.badRequest(`Достижение с таким названием уже существует`)
            }
            const achievement = await Achievement.create({achievementName, description, imageUrl: fileName});
            img.mv(path.resolve(__dirname, '..', 'static\/achievement_img', fileName))

            return res.json(achievement)
        } catch (e) {
            next(ApiError.badRequest(e.message))
        }

    }

    async update(req, res, next) { 
        try{
        const {id} = req.params
        const {achievementName, description} = req.body
        if(!id)
        {
            return next(ApiError.badRequest('Не задан ID'))
        }
        if(achievementName)
        {
            const achie = await Achievement.findOne({
                where: { 
                    achievementName: achievementName
                }
            })

            if(achie){
                throw ApiError.badRequest(`Достижение с таким названием уже существует`)
            }
            const achievement = await Achievement.update({achievementName}, {
                where: {id}
              })
        }
        if(description)
        {
            const achievement = await Achievement.update({description}, {
                where: {id}
              })
        }
        if(req.files)
        { 
            const {img} = req.files
            const {imageUrl} = await Achievement.findOne(
            {
                where: {id}
            },
            )

            try {
                fs.unlinkSync(path.resolve(__dirname, '..', 'static\/achievement_img', imageUrl))
                console.log("Successfully deleted the file.")
              } catch(err) {
                ApiError.internal('Не удалось удалить файл')
              }
           
            let fileName = uuid.v4() + ".jpg"
            img.mv(path.resolve(__dirname, '..', 'static\/achievement_img', fileName))

            try{
            const achievement = await Achievement.update({imageUrl: fileName }, {
                where: {id}
              })
            }
            catch(err){
                ApiError.badRequest(err)
            }
         
        }
        const achievement = await Achievement.findOne(
            {
                where: {id}
            },)
        return res.json(achievement)
        }
        catch(err){ 
            next(ApiError.badRequest(err.message))
        }
     }

    async getAll(req, res, next) {
        try{
            const achievements = await Achievement.findAll()
            return res.json(achievements)
       }
       catch(e){
            next(ApiError.badRequest(e.message))
       }
    }

    async getAllByUser(req, res, next) {
        try{
        const {userId} = req.params
        const achievementDtos = [AchievementDto]
        const userAchievements = await Achievement.findAll({
            include: [{
              model: User,
              where: {id: userId}
             }]
          })

          for(let i = 0; i<userAchievements.length; i++)
          {
            achievementDtos[i] = new AchievementDto(userAchievements[i])
          }

        return res.json(achievementDtos)
       }
       catch(e){
            next(ApiError.badRequest(e.message))
       }
    }

    async delete(req, res, next) {
        try{
        const {id} = req.params
        const {imageUrl} = await Achievement.findOne({
                where: {id}
            })

        const achie = await Achievement.destroy({
                where: {id}
          })
         
        try {
            fs.unlinkSync(path.resolve(__dirname, '..', 'static\/achievement_img', imageUrl))
            console.log("Successfully deleted the file.")
        } catch(err) {
            next(ApiError.internal('Не удалось удалить файл'))
        }
        return res.json(achie)
       }
       catch(e){
            next(ApiError.badRequest(e.message))
       }
    }

}

module.exports = new AchievementController()
