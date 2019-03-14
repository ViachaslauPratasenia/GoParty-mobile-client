package by.bsuir.proslau.goparty

import android.app.Application
import by.bsuir.proslau.goparty.logic.authorization.AuthManager
import by.bsuir.proslau.goparty.logic.authorization.AuthManagerLogic

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
            private set

        fun checkAuthorization(onSuccess: Runnable, onFailure: Runnable) {
            val authManager = AuthManagerLogic() as AuthManager
            authManager.tryLoginWithStoredInfo(onSuccess, onFailure)
        }
    }
}