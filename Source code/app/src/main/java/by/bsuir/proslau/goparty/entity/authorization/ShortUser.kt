package by.bsuir.proslau.goparty.entity.authorization

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class ShortUser(

    @SerializedName("Id")
    @Expose
    var id: UUID? = null,

    @SerializedName("Email")
    @Expose
    val email: String,

    @SerializedName("UserName")
    @Expose
    val username: String,

    @SerializedName("Name")
    @Expose
    val name: String,

    @SerializedName("Surname")
    @Expose
    val surname: String,

    @SerializedName("Image")
    @Expose
    val image: String
): Serializable {
}