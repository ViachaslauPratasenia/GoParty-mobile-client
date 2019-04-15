package by.bsuir.proslau.goparty.server.events

import by.bsuir.proslau.goparty.db.autorization.AuthStore
import by.bsuir.proslau.goparty.entity.Event
import by.bsuir.proslau.goparty.server.RunnableWithObject
import by.bsuir.proslau.goparty.server.ServerAnswer
import by.bsuir.proslau.goparty.server.TypeOfServerError
import by.bsuir.proslau.goparty.server.events.EventApi
import by.bsuir.proslau.goparty.server.events.EventController
import by.bsuir.proslau.goparty.server.events.ServerEventManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ServerEventLogicManager : ServerEventManager {
    private var eventApi: EventApi
    private var authStore: AuthStore = AuthStore()

    init {
        eventApi = EventController.getApi(authStore.token!!)
    }


    override fun getEvents(
        from: Int,
        count: Int,
        onSuccess: RunnableWithObject<List<Event>>,
        onFailure: RunnableWithObject<TypeOfServerError>
    ) {
        eventApi.getEvents(from, 15).enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                checkError(response.code(), onFailure)
                if (response.body() != null) {
                    onSuccess.init(response.body()!!)
                    onSuccess.run()
                }
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                onFailure.init(TypeOfServerError.INTERNET_DOES_NOT_WORK)
                onFailure.run()
            }
        })
    }

    override fun getEventsByTag(
        tag: String,
        onSuccess: RunnableWithObject<List<Event>>,
        onFailure: RunnableWithObject<TypeOfServerError>
    ) {
        eventApi.getEventsByTag(tag).enqueue(object : Callback<List<Event>> {
            override fun onResponse(call: Call<List<Event>>, response: Response<List<Event>>) {
                checkError(response.code(), onFailure)
                if (response.body() != null) {
                    onSuccess.init(response.body()!!)
                    onSuccess.run()
                }
            }

            override fun onFailure(call: Call<List<Event>>, t: Throwable) {
                onFailure.init(TypeOfServerError.INTERNET_DOES_NOT_WORK)
                onFailure.run()
            }
        })
    }

    override fun getEventById(
        id: String,
        onSuccess: RunnableWithObject<Event>,
        onFailure: RunnableWithObject<TypeOfServerError>
    ) {
        eventApi.getEventById(id).enqueue(object : Callback<Event> {
            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                checkError(response.code(), onFailure)
                if (response.body() != null) {
                    onSuccess.init(response.body()!!)
                    val event = response.body()
                    event
                    onSuccess.run()
                }
            }

            override fun onFailure(call: Call<Event>, t: Throwable) {
                onFailure.init(TypeOfServerError.INTERNET_DOES_NOT_WORK)
                onFailure.run()
            }
        })
    }

    override fun addEvent(
        event: Event,
        onSuccess: RunnableWithObject<Event>,
        onFailure: RunnableWithObject<TypeOfServerError>
    ) {
        eventApi.addEvent(event).enqueue(object : Callback<Event> {
            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                checkError(response.code(), onFailure)
                if (response.body() != null) {
                    onSuccess.init(response.body()!!)
                    onSuccess.run()
                }
            }

            override fun onFailure(call: Call<Event>, t: Throwable) {
                onFailure.init(TypeOfServerError.INTERNET_DOES_NOT_WORK)
                onFailure.run()
            }
        })
    }

    override fun deleteEvent(id: String, onSuccess: Runnable, onFailure: RunnableWithObject<TypeOfServerError>) {
        getServerAnswerFromServer(eventApi.deleteEvent(id), onSuccess, onFailure)
    }


    private fun getServerAnswerFromServer(
        call: Call<ServerAnswer>,
        onSuccess: Runnable,
        onFailure: RunnableWithObject<TypeOfServerError>
    ) {
        call.enqueue(object : Callback<ServerAnswer> {
            override fun onResponse(call: Call<ServerAnswer>, response: Response<ServerAnswer>) {
                checkError(response.code(), onFailure)

                if (response.body() != null) {
                    onSuccess.run()
                }
            }

            override fun onFailure(call: Call<ServerAnswer>, t: Throwable) {
                onFailure.init(TypeOfServerError.INTERNET_DOES_NOT_WORK)
                onFailure.run()
            }
        })
    }

    private fun checkError(code: Int, onFailure: RunnableWithObject<TypeOfServerError>) {
        when (code) {
            404 -> {
                onFailure.init(TypeOfServerError.INFO_IS_ABSENT)
                onFailure.run()
                return
            }
            401 -> {
                onFailure.init(TypeOfServerError.WRONG_CREDENTIALS)
                onFailure.run()
                return
            }
        }
        if (code / 100 == 5) {
            onFailure.init(TypeOfServerError.SERVER_ERROR)
            onFailure.run()
        }
    }
}