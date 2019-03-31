package by.bsuir.proslau.goparty.entity.authorization

import by.bsuir.proslau.goparty.entity.location.City
import by.bsuir.proslau.goparty.entity.location.Country
import by.bsuir.proslau.goparty.entity.location.Region
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("Email")
    @Expose
    private val email: String,

    @SerializedName("Username")
    @Expose
    private val username: String,

    @SerializedName("Name")
    @Expose
    private val name: String,

    @SerializedName("Surname")
    @Expose
    private val surname: String,

    @SerializedName("Country")
    @Expose
    private val country: Country,

    @SerializedName("Region")
    @Expose
    private val region: Region,

    @SerializedName("City")
    @Expose
    private val city : City,

    @SerializedName("Image")
    @Expose
    private val avatarURL: String
) {}