package by.bsuir.proslau.goparty.logic.events

import by.bsuir.proslau.goparty.ui.all_events.Event
import by.bsuir.proslau.goparty.ui.all_events.EventManager

class EventLogicManager : EventManager {
    override fun getAll(): List<Event> {
        val list : ArrayList<Event> = ArrayList()
        list.add(EventLogic(
            1, "Рок за бобров",
            "03.08.2019", "Боровая, Беларусь",
            "https://pp.userapi.com/c850524/v850524486/c0261/JYSERKwJidM.jpg"))
        list.add(EventLogic(
            2, "Champions League Final", "01.06.2019", "Madrid, Spain",
            "https://pp.userapi.com/c638328/v638328834/10c04/Wjv5jAL_-8E.jpg"))
        list.add(EventLogic(
            3, "Juventus - Atletico", "12.03.2019", "Torino, Italy",
            "https://cdn.images.express.co.uk/img/dynamic/67/590x/Cristiano-Ronaldo-1089767.jpg?r=1550657862236.jpg"))
        list.add(EventLogic(
            4, "Aquaman","21.02.2019", "Minsk, Belarus",
            "https://cdn.arstechnica.net/wp-content/uploads/2018/11/aquaALTTOP-800x542.jpg"
        ))
        return list
    }

    override fun create(event: Event) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(event: Event) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(event: Event) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}