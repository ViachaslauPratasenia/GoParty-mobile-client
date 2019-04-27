package by.bsuir.proslau.goparty.server.events

import by.bsuir.proslau.goparty.entity.Event
import by.bsuir.proslau.goparty.server.RunnableWithObject
import by.bsuir.proslau.goparty.server.TypeOfServerError

interface ServerEventManager {
    fun getEvents(from: Int, count: Int, onSuccess: RunnableWithObject<List<Event>>,
                  onFailure: RunnableWithObject<TypeOfServerError>)

    fun getEventsByTag(tag: String, onSuccess: RunnableWithObject<List<Event>>,
                  onFailure: RunnableWithObject<TypeOfServerError>)

    fun getEventById(id: String, onSuccess: RunnableWithObject<Event>,
        onFailure: RunnableWithObject<TypeOfServerError>)

    fun addEvent(event: Event, onSuccess: RunnableWithObject<Event>,
                 onFailure: RunnableWithObject<TypeOfServerError>)

    fun deleteEvent(id: String, onSuccess: Runnable,
                    onFailure: RunnableWithObject<TypeOfServerError>)
}