package by.bsuir.proslau.goparty.server

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ServerAnswer {
    @SerializedName("success")
    @Expose
    private var success: Boolean? = null
    @SerializedName("access_token")
    private var token: String? = null
    @SerializedName("error")
    @Expose
    private var error: String? = null

    fun getError(): String? {
        return error
    }

    fun getSuccess(): Boolean? {
        return success
    }

    fun getToken(): String? {
        return token
    }

    fun setError(error: String) {
        this.error = error
    }

    fun setSuccess(success: Boolean?) {
        this.success = success
    }

    fun setToken(token: String) {
        this.token = token
    }
}