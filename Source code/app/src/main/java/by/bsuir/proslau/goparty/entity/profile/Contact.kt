package by.bsuir.proslau.goparty.entity.profile

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Contact(
    @SerializedName("Type")
    @Expose
    private val type : Int,

    @SerializedName("Value")
    @Expose
    private val value : String
) {}