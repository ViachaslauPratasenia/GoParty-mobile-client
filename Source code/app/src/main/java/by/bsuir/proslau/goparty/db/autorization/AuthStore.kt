package by.bsuir.proslau.goparty.db.autorization

import android.content.SharedPreferences
import by.bsuir.proslau.goparty.App
import by.bsuir.proslau.goparty.entity.authorization.User
import com.google.gson.Gson

import android.content.Context.MODE_PRIVATE

class AuthStore {
    private val preferences: SharedPreferences = App.instance.getSharedPreferences("MyPrefs", MODE_PRIVATE)

    val person: User
        get() {
            val gson = Gson()
            val json = preferences.getString(SAVED_USER, "")
            return gson.fromJson(json, User::class.java)
        }

    val token: String?
        get() = preferences.getString(SAVED_TOKEN, "")

    val login: String?
        get() = preferences.getString(SAVED_LOGIN, "")

    fun saveLogin(login: String) {
        val editor = preferences.edit()
        editor.putString(SAVED_LOGIN, login)
        editor.apply()
    }

    fun saveUser(user: User) {
        val editor = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        editor.putString(SAVED_USER, json)
        editor.apply()
    }

    fun saveToken(token: String) {
        val editor = preferences.edit()
        editor.putString(SAVED_TOKEN, token)
        editor.apply()
    }

    companion object {
        private const val SAVED_LOGIN = "saved_login"
        private const val SAVED_TOKEN = "saved_token"
        private const val SAVED_USER = "saved_user"
    }
}
