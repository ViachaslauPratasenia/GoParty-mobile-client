package by.bsuir.proslau.goparty.ui.all_events

import android.content.Context
import by.bsuir.proslau.goparty.entity.Event

interface EventManager {
    fun getAll(context: Context, start: Int, count: Int) : List<Event>
    fun getById(context: Context, id: String): Event?
    fun create(context: Context, event: Event)
    fun delete(context: Context, event: Event)
}