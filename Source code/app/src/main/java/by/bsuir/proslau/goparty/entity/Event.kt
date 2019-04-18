package by.bsuir.proslau.goparty.entity

import by.bsuir.proslau.goparty.entity.authorization.User
import by.bsuir.proslau.goparty.entity.location.City
import by.bsuir.proslau.goparty.entity.location.Country
import by.bsuir.proslau.goparty.entity.location.Location
import by.bsuir.proslau.goparty.entity.location.Region
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class Event : Serializable{
    @SerializedName("UserId")
    @Expose
    var userId : UUID? = null
        private set

    @SerializedName("Id")
    @Expose
    var eventId: UUID? = null
        private set

    @SerializedName("Name")
    @Expose
    var title : String = ""
        private set

    @SerializedName("Address")
    @Expose
    var address : String = ""
        private set

    @SerializedName("StartTime")
    @Expose
    var startTime : String = ""
        private set

    @SerializedName("Location")
    @Expose
    var location : Location? = null
        private set

    @SerializedName("CreatedBy")
    @Expose
    var user: User? = null

    /*@SerializedName("Country")
    @Expose
    var country: Country? = null
        private set

    @SerializedName("Region")
    @Expose
    var region: Region? = null
        private set

    @SerializedName("City")
    @Expose
    var city : City? = null
        private set*/

    @SerializedName("Image")
    @Expose
    var photoURL : String = ""
        private set

    @SerializedName("Description")
    @Expose
    var description : String = ""
        private set

    @SerializedName("Tags")
    @Expose
    var tags : ArrayList<Int>? = null
        private set

    @SerializedName("QuantityJoined")
    @Expose
    var eventSubcribers : Int = 0
        private set
}