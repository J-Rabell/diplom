const Router = require('express')
const router = new Router()
const accountController = require('../controllers/accountController')

router.post('/', accountController.create)//создать
router.put('/:id', accountController.update)//изменить 
router.get('/:userId', accountController.getOne)//получить
router.get('/', accountController.getAll)//получить 
router.delete('/:id', accountController.deleteOne)//delete 

module.exports = router
