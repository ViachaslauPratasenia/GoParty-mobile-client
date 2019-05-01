package by.bsuir.proslau.goparty.entity.local

import java.io.Serializable

data class UserLocal(
    val id: Int,
    var email: String,
    val username: String,
    val name: String,
    val surname: String,
    var location: String,
    val image: String,
    val eventsId: ArrayList<Int>,
    val password: String,
    val isModer: Int,
    var phone: String,
    var vk: String,
    var facebook: String,
    var ok: String,
    var telegram: String,
    var skype: String
): Serializable {
     companion object {
         var counter = 1
     }
}