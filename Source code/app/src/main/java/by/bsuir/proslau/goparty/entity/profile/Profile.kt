package by.bsuir.proslau.goparty.entity.profile

import by.bsuir.proslau.goparty.entity.location.City
import by.bsuir.proslau.goparty.entity.location.Country
import by.bsuir.proslau.goparty.entity.location.Region
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

    @SerializedName("Country")
    @Expose
    private val country: Country,

    @SerializedName("Region")
    @Expose
    private val region: Region,

    @SerializedName("City")
    @Expose
    private val city : City,

    @SerializedName("Email")
    @Expose
    private val email: String,

    @SerializedName("Image")
    @Expose
    private val photoURL: String,

    @SerializedName("Contacts")
    @Expose
    private val contacts: ArrayList<Contact>
) {
}