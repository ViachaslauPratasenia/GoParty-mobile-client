package by.bsuir.proslau.goparty.server

enum class TypeOfServerError private constructor(val typeOfServerError: String) {
    SERVER_ERROR("Server error"),
    WRONG_CREDENTIALS("Wrong credentials"),
    INFO_IS_ABSENT("Info is absent"),
    INTERNET_DOES_NOT_WORK("Internet does not work")
}
