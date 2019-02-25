package by.bsuir.proslau.goparty.ui.all_events

import java.io.Serializable

interface Event : Serializable{
    val userId : Long
    val title : String
    val date : String
    val location : String
    val photo : String
    val description : String
    val quantityJoined : Int
    val joinedId : ArrayList<Long>
}