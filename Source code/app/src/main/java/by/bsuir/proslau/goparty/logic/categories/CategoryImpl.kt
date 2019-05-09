package by.bsuir.proslau.goparty.logic.categories

import by.bsuir.proslau.goparty.ui.all_events.events_by_category.Category

data class CategoryImpl(
    override val image : Int,
    override val title : String,
    override val code: Int
) : Category