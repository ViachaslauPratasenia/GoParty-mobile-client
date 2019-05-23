package by.bsuir.proslau.goparty.logic.local.events

import android.util.Log
import by.bsuir.proslau.goparty.App
import by.bsuir.proslau.goparty.db.local.EventRepository
import by.bsuir.proslau.goparty.entity.local.EventLocal

class EventLocalManager {
    companion object {
        private const val count = 5
        fun getEventsByPage(page: Int): ArrayList<EventLocal> {
            val baseList: ArrayList<EventLocal> = EventRepository(App.context).findAll()
            val returnedList: ArrayList<EventLocal> = ArrayList()
            val startPosition = count * (page - 1)
            val endPosition = count * page - 1
            return try {
                if(endPosition >= baseList.size){
                    for(i in startPosition until baseList.size){
                        returnedList.add(baseList[i])
                    }
                } else {
                    for(i in startPosition..endPosition){
                        returnedList.add(baseList[i])
                    }
                }
                returnedList
            } catch (e: Exception) {
                ArrayList()
            }
        }
    }
}