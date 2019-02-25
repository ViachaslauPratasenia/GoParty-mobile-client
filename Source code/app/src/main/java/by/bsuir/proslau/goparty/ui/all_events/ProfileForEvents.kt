package by.bsuir.proslau.goparty.ui.all_events

import java.io.Serializable

interface ProfileForEvents : Serializable{
    val id : Long
    val avatar : String
    val username : String
}