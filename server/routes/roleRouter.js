const Router = require('express')
const router = new Router()
const roleController = require('../controllers/roleController')
//const checkRole = require('../middleware/checkRoleMiddleware')

router.post('/', roleController.create)//создать
router.get('/', roleController.getAll)//получить все 
router.put('/:id', roleController.update)//изменить название роли по id

module.exports = router