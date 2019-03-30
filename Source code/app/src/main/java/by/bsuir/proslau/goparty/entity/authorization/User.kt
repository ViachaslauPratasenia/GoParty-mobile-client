package by.bsuir.proslau.goparty.entity.authorization

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("Email")
    @Expose
    private val email: String,

    @SerializedName("Nickname")
    @Expose
    private val nickname: String,

    @SerializedName("Name")
    @Expose
    private val name: String,

    @SerializedName("Surname")
    @Expose
    private val surname: String,

    @SerializedName("City")
    @Expose
    private val city: String,

    @SerializedName("Region")
    @Expose
    private val region: String,

    @SerializedName("Country")
    @Expose
    private val country: String,

    @SerializedName("AvatarUrl")
    @Expose
    private val avatarURL: String
) {}