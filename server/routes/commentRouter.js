const Router = require('express')
const router = new Router()
const commentController = require('../controllers/commentController')
const authMiddleware = require('../middleware/AuthMiddleware');

router.post('/',  commentController.create)//создать
router.put('/:id',  commentController.update)//изменить по id
router.get('/',  commentController.getAll)//получить все комментарии
router.get('/:questionId',  commentController.getAllByQuestion)//получить все комментарии к опросу

module.exports = router
