package by.bsuir.proslau.goparty.logic.authorization

object AuthorizationErrors {
    enum class TypeOfAuthManagerError private constructor(val description: String) {
        SERVER_ERROR("Server error"),
        USER_CHECK_ERROR("Not authorized."),
        WRONG_CREDENTIALS("Wrong credentials"),
        SUCH_EMAIL_ALREADY_REGISTERED("Such email already registered."),
        ACCESS_DENIED("Access denied")
    }

    internal fun convertError(str: String): TypeOfAuthManagerError? {
        if (TypeOfAuthManagerError.ACCESS_DENIED.description == str) return TypeOfAuthManagerError.ACCESS_DENIED
        if (TypeOfAuthManagerError.SERVER_ERROR.description == str) return TypeOfAuthManagerError.SERVER_ERROR
        if (TypeOfAuthManagerError.SUCH_EMAIL_ALREADY_REGISTERED.description == str) return TypeOfAuthManagerError.SUCH_EMAIL_ALREADY_REGISTERED
        if (TypeOfAuthManagerError.USER_CHECK_ERROR.description == str) return TypeOfAuthManagerError.USER_CHECK_ERROR
        return if (TypeOfAuthManagerError.WRONG_CREDENTIALS.description == str) TypeOfAuthManagerError.WRONG_CREDENTIALS else null
    }

}