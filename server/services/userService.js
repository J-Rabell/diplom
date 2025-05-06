const {User, Role_user} = require('../models/models')
const bcrypt = require('bcrypt');
const uuid = require('uuid');
const mailService = require('./mailService');
const tokenService = require('./tokenService');
const UserDto = require('../dtos/user-dto');
const ApiError = require('../error/ApiError');

class UserService {
    async registration(email, password, API_URL) {
        const candidate = await User.findOne({
            where: { 
                email: email
            }
        })
        if (candidate) {
            throw ApiError.badRequest(`Пользователь с почтовым адресом ${email} уже существует`)
        }

        const hashPassword = await bcrypt.hash(password, 3);
        const activationLink = uuid.v4(); 

        const user = await User.create({email, password: hashPassword, activationLink})
        Role_user.create({userId: user.id, roleId: 2}) //установка роли пользователя по умолчанию как USER
        
        await mailService.sendActivationMail(email, `${API_URL}/api/user/activate/${activationLink}`);

        const userDto = new UserDto(user); // id, email, isActivated
        const tokens = tokenService.generateTokens({...userDto});
        await tokenService.saveToken(userDto.id, tokens.refreshToken);

        return {...tokens, user: userDto}
    }

    async activate(activationLink) {
        const user = await User.findOne({
            where: { 
                activationLink: activationLink
            }
        })
        if (!user) {
            throw ApiError.badRequest('Неккоректная ссылка активации')
        }
        user.isActivated = true;
        await user.save();
    }

    async login(email, password) { 
        const user = await User.findOne({
            where: { 
                email: email.toLowerCase()
            }
        })
        if (!user) {
            throw ApiError.badRequest('Пользователь с таким email не найден')
        }
        const isPassEquals = await bcrypt.compare(password, user.password);
        if (!isPassEquals) {
            throw ApiError.badRequest('Неверный пароль');
        }
        const userDto = new UserDto(user);
        const tokens = tokenService.generateTokens({...userDto});

        await tokenService.saveToken(userDto.id, tokens.refreshToken);

        return {...tokens, user: userDto}
    }

    async logout(refreshToken) {
        const token = await tokenService.removeToken(refreshToken);
        return token;
    }

    async refresh(refreshToken) {
        if (!refreshToken) {
            throw ApiError.unauthorizedError();
        }
        const userData = tokenService.validateRefreshToken(refreshToken);//
        const tokenFromDb = await tokenService.findToken(refreshToken);//
        if (!userData || !tokenFromDb) {
            throw ApiError.unauthorizedError();
        }
        const user = await User.findByPk(userData.id)//?
        const userDto = new UserDto(user);
        const tokens = tokenService.generateTokens({...userDto});

        await tokenService.saveToken(userDto.id, tokens.refreshToken);
        return {...tokens, user: userDto}
    }

}

module.exports = new UserService()