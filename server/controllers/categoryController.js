const {Category} = require('../models/models')
const ApiError = require('../error/ApiError');

class CategoryController {
    async create(req, res, next) {
        const {categoryName} = req.body
        if(!categoryName)
        {
            return next(ApiError.badRequest('Имя категории не задано'))
        }
        try{
            const category = await Category.create({categoryName})
            return res.json(category)
        }
        catch(e){
            next(ApiError.badRequest(e.message))
        }
     }
 
     async update(req, res, next) {
         const {id} = req.params
         const {categoryName} = req.body
         if(!id)
         {
             return next(ApiError.badRequest('Не задан ID'))
         }
         if(!categoryName)
         {
             return next(ApiError.badRequest('Имя категории не задано'))
         }
         try{
         const category = await Category.update({categoryName}, {
             where: {id}
           })
           return res.json(category)
        }
        catch(e){
            next(ApiError.badRequest(e.message))
        }
      }
 
     async getAll(req, res, next) {
        try{
         const categoryes = await Category.findAll({
            // Add order conditions here....
            order: [
                ['categoryName', 'ASC'],
            ],
        });
         return res.json(categoryes)
        }
        catch(e){
            next(ApiError.badRequest(e.message))
        }
     }
}

module.exports = new CategoryController()
