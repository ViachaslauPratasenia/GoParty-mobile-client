package by.bsuir.proslau.goparty.logic.authorization

interface AuthManager {
    fun tryLoginWith(login: String, password: String, onSuccess: Runnable, onFailure: RunnableWithError)
    fun tryLoginWithStoredInfo(onSuccess: Runnable, onFailure: Runnable)
    fun getStoreLogin(): String
}