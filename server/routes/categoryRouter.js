const Router = require('express')
const router = new Router()
const categoryController = require('../controllers/categoryController')
//const checkRole = require('../middleware/checkRoleMiddleware')

router.post('/', categoryController.create)//создать
router.put('/:id', categoryController.update)//изменить по id статус 
router.get('/', categoryController.getAll)//получить все категории

module.exports = router