package by.bsuir.proslau.goparty.logic.authorization

import by.bsuir.proslau.goparty.db.autorization.AuthStore
import by.bsuir.proslau.goparty.entity.authorization.AuthInfo
import by.bsuir.proslau.goparty.entity.authorization.User
import by.bsuir.proslau.goparty.server.ServerAnswer
import com.google.gson.Gson
import by.bsuir.proslau.goparty.server.authorization.AuthApi
import by.bsuir.proslau.goparty.server.authorization.AuthController
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthManagerLogic : AuthManager {

    private var error: AuthorizationErrors.TypeOfAuthManagerError? = null
    private val store: AuthStore = AuthStore()
    private val authApi: AuthApi = AuthController.api

    override fun tryLoginWithStoredInfo(onSuccess: Runnable, onFailure: Runnable) {
        tryLoginWithToken(store.token!!, onSuccess, onFailure)
    }

    override fun getStoreLogin(): String {
        return store.login!!
    }

    override fun tryLoginWith(login: String, password: String, onSuccess: Runnable, onFailure: RunnableWithError) {
        val onFailureUserCheck = Runnable {
            onFailure.init(error)
            onFailure.run()
        }

        val authInfo = AuthInfo(login, password)

        authApi.checkLogin(authInfo).enqueue(object : Callback<ServerAnswer> {
            override fun onResponse(call: Call<ServerAnswer>, response: Response<ServerAnswer>) {

                if (response.errorBody() != null) {
                    val gson = Gson()
                    val serverAnswer = gson.fromJson(response.errorBody()!!.charStream(), ServerAnswer::class.java)
                    error = AuthorizationErrors.convertError(serverAnswer.getError()!!)
                    onFailureUserCheck.run()
                    return
                }
                if (response.body()!!.getSuccess()!! && response.body()!!.getToken() != null) {
                    store.saveToken(response.body()!!.getToken()!!)
                    store.saveLogin(login)
                    saveUser()
                    onSuccess.run()
                } else {
                    error = AuthorizationErrors.TypeOfAuthManagerError.USER_CHECK_ERROR
                    onFailureUserCheck.run()
                }
            }

            override fun onFailure(call: Call<ServerAnswer>, t: Throwable) {
                error = AuthorizationErrors.TypeOfAuthManagerError.SERVER_ERROR
                onFailureUserCheck.run()
            }
        })
    }

    private fun saveUser() {
        authApi.getUser(store.token!!).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.body() != null) {
                    store.saveUser(response.body()!!)
                }
            }
            override fun onFailure(call: Call<User>, t: Throwable) {

            }
        })
    }

    private fun tryLoginWithToken(token: String, onSuccess: Runnable, onFailure: Runnable) {
        authApi.getUser(token).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.body() != null) {
                    onSuccess.run()
                } else {
                    onFailure.run()
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                onFailure.run()
            }
        })
    }

}