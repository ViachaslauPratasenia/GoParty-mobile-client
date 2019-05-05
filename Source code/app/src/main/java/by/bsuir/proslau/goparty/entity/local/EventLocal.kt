package by.bsuir.proslau.goparty.entity.local

import java.io.Serializable

data class EventLocal(
    var id: Int,
    var createdBy: Int,
    var name: String,
    var address: String,
    var startTime: String,
    var location: String,
    var description: String,
    var image: String,
    var tags: Int,
    var eventSubscribers: Int,
    var subscribersId: ArrayList<Int>
): Serializable {
    companion object {
        var counter = 1
    }
}