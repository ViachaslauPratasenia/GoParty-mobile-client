package by.bsuir.proslau.goparty.db.local

import android.content.Context
import android.util.Log
import by.bsuir.proslau.goparty.db.base.database
import by.bsuir.proslau.goparty.entity.local.EventLocal
import by.bsuir.proslau.goparty.utils.DatabaseArrayConverter
import org.jetbrains.anko.db.*

class EventRepository(val context: Context) {
    fun findAll() : ArrayList<EventLocal> = context.database.use {
        val notes = ArrayList<EventLocal>()

        select("events", "id", "createdBy", "name", "address", "startTime", "location", "description", "image",
            "tags", "eventSubscribers", "subscribersId")
            .parseList(object: MapRowParser<List<EventLocal>> {
                override fun parseRow(columns: Map<String, Any?>): List<EventLocal> {
                    val id = columns.getValue("id")
                    val createdBy = columns.getValue("createdBy")
                    val name = columns.getValue("name")
                    val address = columns.getValue("address")
                    val startTime = columns.getValue("startTime")
                    val location = columns.getValue("location")
                    val description = columns.getValue("description")
                    val image = columns.getValue("image")
                    val tags = columns.getValue("tags")
                    val eventSubscribers = columns.getValue("eventSubscribers")
                    val subscribersId = columns.getValue("subscribersId")

                    val note = EventLocal(id.toString().toInt(), createdBy.toString().toInt(),
                        name.toString(), address.toString(), startTime.toString(),
                        location.toString(), description.toString(), image.toString(),
                        tags.toString().toInt(), eventSubscribers.toString().toInt(),
                        //DatabaseArrayConverter.convertToArray(subscribersId.toString(), "event ${id.toString().toInt()}"))
                        DatabaseArrayConverter.jsonToArray(subscribersId.toString()))
                    notes.add(note)
                    Log.e("repository", "note ${note.name} find")
                    return notes
                }
            })
        notes
    }

    fun findByTag(tag: Int) : ArrayList<EventLocal> = context.database.use {
        val notes = ArrayList<EventLocal>()

        select("events", "id", "createdBy", "name", "address", "startTime", "location", "description", "image",
            "tags", "eventSubscribers", "subscribersId")
            .whereArgs("(tag = {eventTag}) ", "eventTag" to tag)
            .parseList(object: MapRowParser<List<EventLocal>> {
                override fun parseRow(columns: Map<String, Any?>): List<EventLocal> {
                    val id = columns.getValue("id")
                    val createdBy = columns.getValue("createdBy")
                    val name = columns.getValue("name")
                    val address = columns.getValue("address")
                    val startTime = columns.getValue("startTime")
                    val location = columns.getValue("location")
                    val description = columns.getValue("description")
                    val image = columns.getValue("image")
                    val tags = columns.getValue("tags")
                    val eventSubscribers = columns.getValue("eventSubscribers")
                    val subscribersId = columns.getValue("subscribersId")

                    val note = EventLocal(id.toString().toInt(), createdBy.toString().toInt(),
                        name.toString(), address.toString(), startTime.toString(),
                        location.toString(), description.toString(), image.toString(),
                        tags.toString().toInt(), eventSubscribers.toString().toInt(),DatabaseArrayConverter
                            .jsonToArray(subscribersId.toString()))
                    notes.add(note)

                    return notes
                }
            })
        notes
    }

    fun findById(id: Int): EventLocal = context.database.use {
        lateinit var note: EventLocal

        select("events", "id", "createdBy", "name", "address", "startTime", "location", "description", "image",
            "tags", "eventSubscribers", "subscribersId")
            .whereArgs("(id = {eventId}) ", "eventId" to id)
            .parseSingle(object: MapRowParser<EventLocal> {
                override fun parseRow(columns: Map<String, Any?>): EventLocal {
                    val createdBy = columns.getValue("createdBy")
                    val name = columns.getValue("name")
                    val address = columns.getValue("address")
                    val startTime = columns.getValue("startTime")
                    val location = columns.getValue("location")
                    val description = columns.getValue("description")
                    val image = columns.getValue("image")
                    val tags = columns.getValue("tags")
                    val eventSubscribers = columns.getValue("eventSubscribers")
                    val subscribersId = columns.getValue("subscribersId")

                    note = EventLocal(id, createdBy.toString().toInt(), name.toString(), address.toString(), startTime.toString(),
                        location.toString(), description.toString(), image.toString(),
                        tags.toString().toInt(), eventSubscribers.toString().toInt(),DatabaseArrayConverter
                            .jsonToArray(subscribersId.toString()))
                    return note
                }
            })
        note
    }

    fun create(event: EventLocal) = context.database.use {
        insert("events",
            "id" to event.id,
            "createdBy" to event.createdBy,
            "name" to event.name,
            "address" to event.address,
            "startTime" to event.startTime,
            "location" to event.location,
            "description" to event.description,
            "image" to event.image,
            "tags" to event.tags,
            "eventSubscribers" to event.eventSubscribers,
            "subscribersId" to DatabaseArrayConverter.convertToString("event ${event.id}", event.subscribersId)
        )
        Log.e("insert", "${event.id} is created")
    }

    fun update(event: EventLocal) = context.database.use {
        update("events",
            "id" to event.id,
            "createdBy" to event.createdBy,
            "name" to event.name,
            "address" to event.address,
            "startTime" to event.startTime,
            "location" to event.location,
            "description" to event.description,
            "image" to event.image,
            "tags" to event.tags,
            "eventSubscribers" to event.eventSubscribers,
            "subscribersId" to event.subscribersId)
            .whereArgs("id = {eventId}", "eventId" to event.id)
            .exec()
    }

    fun delete(event: EventLocal) = context.database.use{
        delete("users", whereClause = "id = {eventId}", args = *arrayOf("eventId" to event.id))
    }

    fun getByTag(tag: Int): ArrayList<EventLocal>{
        return findAll().filter {
            it.tags == tag
        } as ArrayList
    }
}