package by.bsuir.proslau.goparty.entity.local

import java.io.Serializable

data class UserLocal(
    val id: Int,
    val email: String,
    val username: String,
    val name: String,
    val surname: String,
    val location: String,
    val image: String,
    val eventsId: ArrayList<Int>,
    val password: String,
    val isModer: Int,
    val phone: String,
    val vk: String,
    val facebook: String,
    val ok: String,
    val telegram: String,
    val skype: String
): Serializable {
     companion object {
         var counter = 1
     }
}