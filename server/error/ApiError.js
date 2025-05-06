class ApiError extends Error{
    status;
    errors;
    
    constructor(status, message, errors = []) {
        super(message);
        this.status = status;
        this.errors = errors;
    }

    static badRequest(message) {
        return new ApiError(404, message)
    }

    static internal(message) {
        return new ApiError(500, message)
    }

    static forbidden(message) {
        return new ApiError(403, message)
    }

    static noAccessError() {
        return new ApiError(403, 'Нет доступа')
    }

    static unauthorizedError() {
        return new ApiError(401, 'Пользователь не авторизован')
    }

    static badRequestErrors(message, errors = []) {
        return new ApiError(400, message, errors);
    }
}

module.exports = ApiError
