package by.bsuir.proslau.goparty.entity.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Contacts(
    @SerializedName("phoneNumber")
    @Expose
    private val phoneNumber : String,

    @SerializedName("vk")
    @Expose
    private val vk : String,

    @SerializedName("facebook")
    @Expose
    private val facebook : String,

    @SerializedName("telegram")
    @Expose
    private val telegram : String,

    @SerializedName("skype")
    @Expose
    private val skype : String,

    @SerializedName("ok")
    @Expose
    private val ok : String
) {}