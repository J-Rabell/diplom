require('dotenv').config()

const express = require('express')
const sequelize = require('./db')
const models = require('./models/models')
const cors = require('cors')
const cookieParser = require('cookie-parser') 
const fileUpload = require('express-fileupload')
//const { RowDescriptionMessage } = require('pg-protocol/dist/messages')
const router = require('./routes/index')
const errorHandler = require('./middleware/ErrorHandlingMiddleware')
const path = require('path')

const PORT = process.env.PORT || 5000

const app = express()

app.get('/', (req, res) => res.send( req.protocol + '://' + req.get('host')));
app.get('/page', (req, res) => res.send("<h2>Почта подтверждена!</h2>"));

app.use(cors())
app.use(express.json())
app.use(cookieParser())
app.use(express.static(path.resolve(__dirname, 'static/question_img')))
app.use(express.static(path.resolve(__dirname, 'static/avatar_img')))
app.use(express.static(path.resolve(__dirname, 'static/achievement_img')))
app.use(fileUpload({}))
app.use('/api', router)


// Обработка ошибок, последний Middleware
app.use(errorHandler)

const start = async () => {
    try {
        await sequelize.authenticate()
        await sequelize.sync()
        app.listen(PORT, () => console.log(`Server started on port ${PORT}`))
    } catch (e) {
        console.log(e)
    }
}

start()
