package by.bsuir.proslau.goparty.logic.categories

import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.ui.all_events.events_by_category.CategoriesManager
import by.bsuir.proslau.goparty.ui.all_events.events_by_category.Category

class CategoriesLogicManager : CategoriesManager {
    override fun getAll(): List<Category> {
        val list : ArrayList<Category> = ArrayList()
        list.add(CategoryImpl(R.drawable.travel_category, "Travel", 1))
        list.add(CategoryImpl(R.drawable.fiml_category, "Film", 2))
        list.add(CategoryImpl(R.drawable.sport_category, "Sport", 3))
        list.add(CategoryImpl(R.drawable.concert_category, "Concert", 4))
        list.add(CategoryImpl(R.drawable.spectacle_category, "Spectacle", 5))
        return list
    }

    fun getByCode(code: Int): Category{
        return getAll().first {
            it.code == code
        }
    }
}