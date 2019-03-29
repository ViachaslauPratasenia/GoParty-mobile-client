package by.bsuir.proslau.goparty.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Event(
    @SerializedName("userId")
    @Expose
    private val userId : Long,

    @SerializedName("title")
    @Expose
    private val title : String,

    @SerializedName("date")
    @Expose
    private val date : String,

    @SerializedName("location")
    @Expose
    private val location : String,

    @SerializedName("photoURL")
    @Expose
    private val photoURL : String,

    @SerializedName("description")
    @Expose
    private val description : String,

    @SerializedName("tags")
    @Expose
    private val tags : ArrayList<Int>,

    @SerializedName("quantityJoined")
    @Expose
    private val quantityJoined : Int,

    @SerializedName("joinedNicks")
    @Expose
    private val joinedNicks : ArrayList<String>
) {}