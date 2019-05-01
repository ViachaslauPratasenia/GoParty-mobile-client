package by.bsuir.proslau.goparty

import android.app.Application
import android.content.Context
import by.bsuir.proslau.goparty.db.base.addEvents
import by.bsuir.proslau.goparty.logic.authorization.AuthManager
import by.bsuir.proslau.goparty.logic.authorization.AuthManagerLogic

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        App.context = applicationContext
    }

    companion object {
        lateinit var instance: App
            private set

        fun checkAuthorization(onSuccess: Runnable, onFailure: Runnable) {
            val authManager = AuthManagerLogic() as AuthManager
            authManager.tryLoginWithStoredInfo(onSuccess, onFailure)
        }

        lateinit var context: Context
    }
}