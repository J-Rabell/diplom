const {Account} = require('../models/models')
const ApiError = require('../error/ApiError')
const uuid = require('uuid')
const path = require('path')
const fs = require("fs");

class AccountController {
    async create(req, res, next) {
        try {
            let {userId, nickname} = req.body
            const account = await Account.create({nickname, userId: userId});
            return res.json(account)
        } catch (e) {
            next(ApiError.badRequest(e.message))
        }
    }

    async update(req, res, next) {
       
        try{
            const {id} = req.params //id аккаунта
            const {nickname, description} = req.body
            if(!id)
            {
                return next(ApiError.badRequest('Не задан ID'))
            }
    
            if(nickname)
            {
                const acc = await Account.update({nickname: nickname}, {
                    where: {id: id}
                  })
            }
            if(description)
            {
                const acc = await Account.update({description: description}, {
                    where: {id}
                  })
              
            }
            if(req.files)
            { 
                const {img} = req.files
                const {imageUrl} = await Account.findOne(
                {
                    where: {id}
                },
                )

                if(imageUrl!=null){
                    try {
                        fs.unlinkSync(path.resolve(__dirname, '..', 'static\/avatar_img', imageUrl))
                        console.log("Successfully deleted the file.")
                       
                      } catch(err) {
                        ApiError.internal('Не удалось удалить файл')
                      
                      }
                }

                let fileName = uuid.v4() + ".jpg"
                img.mv(path.resolve(__dirname, '..', 'static\/avatar_img', fileName))
    
                try{
                const acc = await Account.update({imageUrl: fileName }, {
                    where: {id}
                  })
                }
                catch(err){
                    ApiError.badRequest(err)
                }
             
            }
            const acc = await Account.findOne(
                {
                    where: {id}
                },)
            return res.json(acc)
            }
            catch(err){ 
                next(ApiError.badRequest(err.message))
            }
      
     }
 

    async getOne(req, res, next) {
        try {
            let {userId} = req.params
          
            const account = await Account.findOne({
                where: { 
                    userId: userId
                }
            });
            return res.json(account)
        } catch (e) {
            next(ApiError.badRequest(e.message))
        }
    }

    async deleteOne(req, res, next) {
        try{
            const {id} = req.params
            if(!id)
            {
                return next(ApiError.badRequest('Не задан ID'))
            }

            const {imageUrl} = await Account.findOne({
                where: {id: id}
            })
            const q = await Account.destroy({
                where: {id}
              })
    
              if(imageUrl!=null){
                try {
                    fs.unlinkSync(path.resolve(__dirname, '..', 'static\/avatar_img', imageUrl))
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

    async getAll(req, res, next) {
        try {
            const accounts = await Account.findAll()
            return res.json(accounts)
        } catch (e) {
            next(ApiError.badRequest(e.message))
        }
    }


}

module.exports = new AccountController()
