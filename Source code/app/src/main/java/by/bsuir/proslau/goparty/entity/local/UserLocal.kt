package by.bsuir.proslau.goparty.entity.local

import java.io.Serializable

data class UserLocal(
    val id: Int,
    var email: String,
    var username: String,
    var name: String,
    var surname: String,
    var location: String,
    var image: String,
    var eventsId: ArrayList<Int>,
    var password: String,
    var isModer: Int,
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