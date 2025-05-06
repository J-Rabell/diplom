const Router = require('express')
const router = new Router()
const achievementController = require('../controllers/achievementController')


router.post('/', achievementController.create)//создать
router.delete('/:id', achievementController.delete)//удалить
router.put('/:id', achievementController.update)//изменить по id  
router.get('/', achievementController.getAll)//получить все достижения
router.get('/:userId', achievementController.getAllByUser)//получить все достижения пользователя


module.exports = router
