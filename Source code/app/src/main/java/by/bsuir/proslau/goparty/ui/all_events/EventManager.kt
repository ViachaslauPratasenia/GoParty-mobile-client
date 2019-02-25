package by.bsuir.proslau.goparty.ui.all_events

interface EventManager {
    fun getAll() : List<Event>
    fun create(event: Event)
    fun update(event: Event)
    fun delete(event: Event)
}