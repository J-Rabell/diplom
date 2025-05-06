const ApiError = require('../error/ApiError');
const {User} = require('../models/models');

module.exports = async function (req, res, next) {
    try {
    
        const {email} = req.body
        const u = await User.findOne({where: {email: email.toLowerCase()}})
        if (u.isActivated==false) {
            return next(ApiError.badRequest("Подтвердите почту"));
        }

        next();
    } catch (e) {
        return next(ApiError.unauthorizedError());
    }
};
