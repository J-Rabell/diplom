const Router = require('express')
const router = new Router()
const questionController = require('../controllers/questionController')

router.post('/', questionController.create)//создать опрос
router.post('/vote', questionController.vote)//проголосовать в опросе
router.put('/:id', questionController.update)//обновить опрос
router.delete('/vote', questionController.deleteVote) //удалить голос
router.delete('/:id', questionController.deleteOne) //удалить опрос
router.get('/', questionController.getAll)//получить все опросы
router.get('/:id', questionController.getOne) //получить один опрос

module.exports = router