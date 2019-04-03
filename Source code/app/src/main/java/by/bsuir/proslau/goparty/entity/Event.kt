package by.bsuir.proslau.goparty.entity

import by.bsuir.proslau.goparty.entity.location.City
import by.bsuir.proslau.goparty.entity.location.Country
import by.bsuir.proslau.goparty.entity.location.Region
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

class Event{
    @SerializedName("UserId")
    @Expose
    var userId : UUID? = null
        private set

    @SerializedName("Title")
    @Expose
    var title : String = ""
        private set

    @SerializedName("Address")
    @Expose
    var address : String = ""
        private set

    @SerializedName("Date")
    @Expose
    var date : String = ""
        private set

    @SerializedName("Country")
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
        private set

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

    @SerializedName("EventSubcribers")
    @Expose
    var eventSubcribers : Int = 0
        private set
}