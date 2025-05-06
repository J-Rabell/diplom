const {User, Account} = require('../models/models')
const userService = require('../services/userService');
const {validationResult} = require('express-validator');
const ApiError = require('../error/ApiError');
const path = require('path')
const fs = require("fs");

class UserController {
    async registration(req, res, next) {
        try {
            const errors = validationResult(req);
            if (!errors.isEmpty()) {
                return next(ApiError.badRequestErrors('Ошибка при валидации', errors.array()))
            }
            const {nickname, email, password} = req.body;

            const userData = await userService.registration(email.toLowerCase(), password, req.protocol + '://' + req.get('host'));

            const a = await Account.create({nickname: nickname, userId: userData.user.id})

            res.cookie('refreshToken', userData.refreshToken, {maxAge: 30 * 24 * 60 * 60 * 1000, httpOnly: true})
            return res.json(userData);
        } catch (e) {
            next(e);
        }
    }

    async login(req, res, next) {
        try {
            const {email, password} = req.body;
            const userData = await userService.login(email, password);
            res.cookie('refreshToken', userData.refreshToken, {maxAge: 30 * 24 * 60 * 60 * 1000, httpOnly: true})
            return res.json(userData);
        } catch (e) {
            next(e);
        }
    }

    async logout(req, res, next) {
        try {
            const {refreshToken} = req.cookies;
            const token = await userService.logout(refreshToken);
            res.clearCookie('refreshToken');
            return res.json(token);
        } catch (e) {
            next(e);
        }
    }

    async activate(req, res, next) {
        
        try {
            const activationLink = req.params.link;
            await userService.activate(activationLink);
            return res.redirect("/page");
        } catch (e) {
            next(e);
        }
    }

    async refresh(req, res, next) {
        try {
            const {refreshToken} = req.cookies;
            const userData = await userService.refresh(refreshToken);
            res.cookie('refreshToken', userData.refreshToken, {maxAge: 30 * 24 * 60 * 60 * 1000, httpOnly: true})
            return res.json(userData);
        } catch (e) {
            next(e);
        }
    }


    async deleteUser(req, res, next) {
        try{
        const {id} = req.params
        const {imageUrl} = await Account.findOne({
            where: {userId: id}
        })
        const user = await User.destroy({
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
        return res.json(user)
        }
        catch(e){
            next(ApiError.badRequest(e.message))
        }
    }

    async getUsers(req, res) {
        const users = await User.findAll()
        return res.json(users)
    }
}

module.exports = new UserController()
