package by.bsuir.proslau.goparty.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Event(
    @SerializedName("UserId")
    @Expose
    private val userId : Long,

    @SerializedName("Title")
    @Expose
    private val title : String,

    @SerializedName("Address")
    @Expose
    private val address : String,

    @SerializedName("Date")
    @Expose
    private val date : String,

    @SerializedName("Location")
    @Expose
    private val location : Location,

    @SerializedName("PhotoUrl")
    @Expose
    private val photoURL : String,

    @SerializedName("Description")
    @Expose
    private val description : String,

    @SerializedName("Tags")
    @Expose
    private val tags : ArrayList<Int>,

    @SerializedName("EventSubcribers")
    @Expose
    private val eventSubcribers : Int
) {}