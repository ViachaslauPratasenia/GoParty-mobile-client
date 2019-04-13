package by.bsuir.proslau.goparty.logic.events

import android.content.Context
import android.widget.Toast
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.db.autorization.AuthStore
import by.bsuir.proslau.goparty.entity.Event
import by.bsuir.proslau.goparty.server.RunnableWithObject
import by.bsuir.proslau.goparty.server.ServerAnswer
import by.bsuir.proslau.goparty.server.TypeOfServerError
import by.bsuir.proslau.goparty.server.events.EventApi
import by.bsuir.proslau.goparty.server.events.EventController
import by.bsuir.proslau.goparty.server.events.ServerEventLogicManager
import by.bsuir.proslau.goparty.ui.all_events.EventManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EventLogicManager(context: Context) : EventManager {
    @Volatile private var instance: EventLogicManager? = null

    private var eventApi: EventApi
    private var authStore: AuthStore = AuthStore()

    private val errorType = ""

    private var start = 1
    private var count = 15

    init {
        eventApi = EventController.getApi(authStore.token!!)
    }

    fun getEventByPage(context: Context, page: Int, cityId: Int): List<Event>{
        if(page == 1){
            return getAll(context, start, count, cityId)
        }
        //Ну вроде должно работать, логика тут такая = page 7 = 91-105 events
        return getAll(context, count * (page - 1) + 1, count, cityId)
    }

    override fun getAll(context: Context, start: Int, count: Int, cityId: Int): List<Event> {
        val list = ArrayList<Event>()
        eventApi.getEvents(start, count, cityId).enqueue(object : Callback<List<by.bsuir.proslau.goparty.entity.Event>> {
            override fun onResponse(call: Call<List<by.bsuir.proslau.goparty.entity.Event>>,
                                    response: Response<List<by.bsuir.proslau.goparty.entity.Event>>) {
                if(checkError(response.code(), errorType)){
                    Toast.makeText(context, errorType, Toast.LENGTH_LONG).show()
                }
                if (response.body() != null) {
                    val responseList : List<Event> = response.body()!!
                    list.addAll(responseList)
                }
            }
            override fun onFailure(call: Call<List<by.bsuir.proslau.goparty.entity.Event>>, t: Throwable) {
                Toast.makeText(context, TypeOfServerError.INTERNET_DOES_NOT_WORK.typeOfServerError, Toast.LENGTH_LONG).show()
            }
        })
        return list
    }

    override fun getById(context: Context, id: String): Event? {
        var event: Event? = null
        eventApi.getEventById(id).enqueue(object : Callback<Event> {
            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                if(checkError(response.code(), errorType)) {
                    Toast.makeText(context, errorType, Toast.LENGTH_LONG).show()
                }
                if (response.body() != null) {
                    event = response.body()
                }
            }

            override fun onFailure(call: Call<Event>, t: Throwable) {
                Toast.makeText(context, TypeOfServerError.INTERNET_DOES_NOT_WORK.typeOfServerError, Toast.LENGTH_LONG).show()
            }
        })
        return event
    }

    override fun create(context: Context, event: Event) {
        eventApi.addEvent(event).enqueue(object : Callback<Event> {
            override fun onResponse(call: Call<Event>, response: Response<Event>) {
                if(checkError(response.code(), errorType)) {
                    Toast.makeText(context, errorType, Toast.LENGTH_LONG).show()
                }
                if (response.body() != null) {
                    Toast.makeText(context, context.getString(R.string.add_event_success), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<Event>, t: Throwable) {
                Toast.makeText(context, TypeOfServerError.INTERNET_DOES_NOT_WORK.typeOfServerError, Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun delete(context: Context, event: Event) {
        eventApi.deleteEvent(event.eventId.toString()).enqueue(object : Callback<ServerAnswer> {
            override fun onResponse(call: Call<ServerAnswer>, response: Response<ServerAnswer>) {
                if (checkError(response.code(), errorType)) {
                    Toast.makeText(context, errorType, Toast.LENGTH_LONG).show()
                }
                if (response.body() != null) {
                    Toast.makeText(context, context.getString(R.string.delete_event_success), Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ServerAnswer>, t: Throwable) {
                Toast.makeText(context, TypeOfServerError.INTERNET_DOES_NOT_WORK.typeOfServerError, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun checkError(code: Int, error: String): Boolean {
        when (code) {
            404 -> {
                error.plus(TypeOfServerError.INFO_IS_ABSENT.typeOfServerError)
                return true
            }
            401 -> {
                error.plus(TypeOfServerError.WRONG_CREDENTIALS.typeOfServerError)
                return true
            }
        }
        if (code / 100 == 5) {
            error.plus(TypeOfServerError.SERVER_ERROR.typeOfServerError)
        }
        return false
    }
}