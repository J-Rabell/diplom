require('dotenv').config()

const {pg} = require('pg')
const {Sequelize} = require('sequelize')

module.exports = new Sequelize(process.env.DB_URL, {
  dialectModule: pg
});