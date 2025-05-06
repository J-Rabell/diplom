const Router = require('express')
const router = new Router()
const ratingQuestionController = require('../controllers/ratingQuestionController')
const ratingCommentController = require('../controllers/ratingCommentController')

router.post('/question/',  ratingQuestionController.addRate) 
router.put('/question',  ratingQuestionController.updateRate) 
router.delete('/question',  ratingQuestionController.deleteRate)

//обновить получение комментов и добавить туда так же поле лайкал или дизлайкал пользователь коммент когда-либо
router.post('/comment', ratingCommentController.addRate) 
router.put('/comment', ratingCommentController.updateRate) 
router.delete('/comment', ratingCommentController.deleteRate)

module.exports = router
