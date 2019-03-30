package by.bsuir.proslau.goparty.entity.authorization

import by.bsuir.proslau.goparty.entity.Location
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

    @SerializedName("Location")
    @Expose
    private val location: Location,

    @SerializedName("AvatarUrl")
    @Expose
    private val avatarURL: String
) {}