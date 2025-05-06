const Router = require('express')
const router = new Router()
const questionRouter = require('./questionRouter')
const userRouter = require('./userRouter')
const categoryRouter = require('./categoryRouter')
const ratingRouter = require('./ratingRouter')
const commentRouter = require('./commentRouter')
const achievementRouter = require('./achievementRouter')
const accountRouter = require('./accountRouter')
const roleRouter = require('./roleRouter')

router.use('/user', userRouter)// done?
router.use('/category', categoryRouter)//done
router.use('/question', questionRouter)//+-
router.use('/rating', ratingRouter)// ?
router.use('/comment', commentRouter)//done
router.use('/achievement', achievementRouter)// +-
router.use('/account', accountRouter)//done
router.use('/role', roleRouter)//done

module.exports = router