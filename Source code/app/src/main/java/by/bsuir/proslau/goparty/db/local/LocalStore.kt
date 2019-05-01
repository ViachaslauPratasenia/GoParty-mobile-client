package by.bsuir.proslau.goparty.db.local

import android.content.Context
import android.content.SharedPreferences
import by.bsuir.proslau.goparty.App
import by.bsuir.proslau.goparty.entity.local.UserLocal
import com.google.gson.Gson

class LocalStore {
    private val preferences: SharedPreferences = App.instance.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
    val user: UserLocal
        get() {
            val gson = Gson()
            val json = preferences.getString(LocalStore.SAVED_USER, "")
            return gson.fromJson(json, UserLocal::class.java)
        }

    val userId: Int?
        get() = preferences.getInt(SAVED_ID, 0)

    fun saveUserId(userId: Int){
        val editor = preferences.edit()
        editor.putInt(SAVED_ID, userId)
        editor.apply()
    }

    fun saveUser(user: UserLocal) {
        val editor = preferences.edit()
        val gson = Gson()
        val json = gson.toJson(user)
        editor.putString(LocalStore.SAVED_USER, json)
        editor.apply()
    }

    companion object {
        private const val SAVED_USER = "saved_user"
        private const val SAVED_ID = "saved_id"
    }
}