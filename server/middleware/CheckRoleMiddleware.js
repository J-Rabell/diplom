const jwt = require('jsonwebtoken');
const ApiError = require('../error/ApiError');
const sequelize = require('../db')
const { QueryTypes } = require('sequelize')

module.exports = function(role) {
    return async function (req, res, next) {

        if (req.method === "OPTIONS") {
            next()
        }
        try {
            const decoded = req.user;

            await sequelize.query('select GetUserRoles(:_id) as "roleName";', {replacements: { _id: decoded.id },  type: QueryTypes.SELECT}).then(result => {
            if (result)
            {
                if(JSON.stringify(result).indexOf(role)<0) {
                    return next(ApiError.noAccessError());
                }
            }})

            req.user = decoded; 
            next()
        } catch (e) {
            return next(ApiError.unauthorizedError());
        }
    };
}



