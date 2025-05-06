const {Role} = require('../models/models')
const ApiError = require('../error/ApiError');

class RoleController {
    async create(req, res, next) {
        const {roleName} = req.body
        if(!roleName)
        {
            return next(ApiError.badRequest('Имя роли не задано'))
        }
        try{
        const role = await Role.create({roleName})
        return res.json(role)
        }
        catch(e){
            next(ApiError.badRequest(e.message))
        }
     }
 
     async update(req, res, next) {
         const {id} = req.params
         const {roleName} = req.body
         if(!id)
         {
             return next(ApiError.badRequest('Не задан ID'))
         }
         if(!roleName)
         {
             return next(ApiError.badRequest('Имя роли не задано'))
         }
         try{
         const role = await Role.update({roleName}, {
             where: {id}
           })
         return res.json(role)
        }
        catch(e){
            next(ApiError.badRequest(e.message))
        }
      }
 
     async getAll(req, res, next) {
        try{
         const roles = await Role.findAll()
         return res.json(roles)
        }
        catch(e){
            next(e.message)
        }
     }
}

module.exports = new RoleController()
