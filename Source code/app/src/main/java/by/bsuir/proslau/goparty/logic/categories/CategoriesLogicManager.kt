package by.bsuir.proslau.goparty.logic.categories

import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.ui.all_events.events_by_category.CategoriesManager
import by.bsuir.proslau.goparty.ui.all_events.events_by_category.Category

class CategoriesLogicManager : CategoriesManager {
    override fun getAll(): List<Category> {
        val list : ArrayList<Category> = ArrayList()
        list.add(CategoryImpl(R.drawable.travel_category, "Travel"))
        list.add(CategoryImpl(R.drawable.fiml_category, "Film"))
        list.add(CategoryImpl(R.drawable.sport_category, "Sport"))
        list.add(CategoryImpl(R.drawable.concert_category, "Concert"))
        list.add(CategoryImpl(R.drawable.spectacle_category, "Spectacle"))
        return list
    }
}