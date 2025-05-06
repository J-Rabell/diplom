const Router = require('express')
const router = new Router()
const {body} = require('express-validator');
const userController = require('../controllers/userController')
const authMiddleware = require('../middleware/AuthMiddleware');
const checkRole = require('../middleware/CheckRoleMiddleware')
const checkActivationAccount = require('../middleware/CheckActivationAccountMiddleware')

router.post('/registration', 
    body('email').isEmail(),
    body('password').isLength({min: 3}), 
    userController.registration 
)
router.post('/login', checkActivationAccount, userController.login)
router.post('/logout',  userController.logout)
router.get('/activate/:link', userController.activate);
router.get('/refresh', userController.refresh)
//authMiddleware, checkRole('ADMIN'),
router.get('/users', userController.getUsers)
router.delete('/:id', userController.deleteUser)


module.exports = router
