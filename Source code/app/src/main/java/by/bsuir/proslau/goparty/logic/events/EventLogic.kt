package by.bsuir.proslau.goparty.logic.events

import by.bsuir.proslau.goparty.ui.all_events.Event

data class EventLogic(
    override val userId: Long,
    override val title: String,
    override val date: String,
    override val location: String,
    override val photo: String
) : Event