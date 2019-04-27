package by.bsuir.proslau.goparty.ui.all_events

import java.io.Serializable
import java.util.*

interface ProfileForEvents : Serializable{
    val id : UUID
    val avatar : String
    val username : String
}