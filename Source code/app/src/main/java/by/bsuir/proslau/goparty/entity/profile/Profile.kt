package by.bsuir.proslau.goparty.entity.profile

import by.bsuir.proslau.goparty.entity.Location
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Profile(
    @SerializedName("Name")
    @Expose
    private val name: String,

    @SerializedName("Surname")
    @Expose
    private val surname: String,

    @SerializedName("Username")
    @Expose
    private val username: String,

    @SerializedName("Location")
    @Expose
    private val location: Location,

    @SerializedName("Email")
    @Expose
    private val email: String,

    @SerializedName("PhotoURL")
    @Expose
    private val photoURL: String,

    @SerializedName("Contacts")
    @Expose
    private val contacts: Contacts
) {
}