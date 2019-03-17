package by.bsuir.proslau.goparty.entity.authorization

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("email")
    @Expose
    private val email: String,

    @SerializedName("nickname")
    @Expose
    private val nickname: String,

    @SerializedName("name")
    @Expose
    private val name: String,

    @SerializedName("surname")
    @Expose
    private val surname: String,

    @SerializedName("location")
    @Expose
    private val location: String,

    @SerializedName("avatarURL")
    @Expose
    private val avatarURL: String
) {}