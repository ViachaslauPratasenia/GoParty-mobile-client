package by.bsuir.proslau.goparty.server.events

import by.bsuir.proslau.goparty.entity.Event
import by.bsuir.proslau.goparty.entity.location.City
import by.bsuir.proslau.goparty.server.ServerAnswer
import retrofit2.Call
import retrofit2.http.*

interface EventApi{
    @GET("Events")
    fun getEvents(@Header("start") from: Int, @Header("count") count: Int): Call<List<Event>>

    @GET("/events")
    fun getEventsByTag(@Path("tag") tag: String): Call<List<Event>>

    @POST("/events")
    fun addEvent(@Body event: Event): Call<Event>

    @DELETE("/events/{id}")
    fun deleteEvent(@Path("id") id: String): Call<ServerAnswer>

    @GET("/events/{id}")
    fun getEventById(@Path("id") id: String): Call<Event>
}