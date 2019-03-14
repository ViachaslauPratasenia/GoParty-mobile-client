package by.bsuir.proslau.goparty.logic.authorization

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName



data class AuthInfo (
    @SerializedName("login")
    @Expose
    private val login: String? = null,

    @SerializedName("password")
    @Expose
    private val password: String? = null
) {}