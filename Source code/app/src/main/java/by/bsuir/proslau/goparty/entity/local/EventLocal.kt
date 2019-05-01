package by.bsuir.proslau.goparty.entity.local

import java.io.Serializable

data class EventLocal(
    val id: Int,
    val createdBy: Int,
    val name: String,
    val address: String,
    val startTime: String,
    val location: String,
    val description: String,
    val image: String,
    val tags: Int,
    val eventSubscribers: Int,
    val subscribersId: ArrayList<Int>
): Serializable {
    companion object {
        var counter = 1
    }
}