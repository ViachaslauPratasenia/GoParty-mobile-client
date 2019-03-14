package by.bsuir.proslau.goparty.logic.authorization

class RunnableWithError : Runnable {
    protected var error: AuthorizationErrors.TypeOfAuthManagerError =
        AuthorizationErrors.TypeOfAuthManagerError.SERVER_ERROR
        private set

    internal fun init(myError: AuthorizationErrors.TypeOfAuthManagerError?): Runnable {
        this.error = myError!!
        return this
    }

    override fun run() {}
}