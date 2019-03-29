package by.bsuir.proslau.goparty.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Profile(
    @SerializedName("name")
    @Expose
    private val name: String,

    @SerializedName("surname")
    @Expose
    private val surname: String,

    @SerializedName("username")
    @Expose
    private val username: String,

    @SerializedName("location")
    @Expose
    private val location: String,

    @SerializedName("email")
    @Expose
    private val email: String,

    @SerializedName("photoURL")
    @Expose
    private val photoURL: String,

    @SerializedName("phoneNumber")
    @Expose
    private val phoneNumber: String,

    @SerializedName("vk")
    @Expose
    private val vk: String,

    @SerializedName("facebook")
    @Expose
    private val facebook: String,

    @SerializedName("telegram")
    @Expose
    private val telegram: String,

    @SerializedName("odnoklassniki")
    @Expose
    private val odnoklassniki: String,

    @SerializedName("skype")
    @Expose
    private val skype: String
) {
}