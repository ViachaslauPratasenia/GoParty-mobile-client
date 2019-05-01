package by.bsuir.proslau.goparty.db.local

import android.content.Context
import by.bsuir.proslau.goparty.db.base.database
import by.bsuir.proslau.goparty.entity.local.UserLocal
import by.bsuir.proslau.goparty.utils.DatabaseArrayConverter
import org.jetbrains.anko.db.MapRowParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.update

class UserRepository(val context: Context) {
    fun findAll() : ArrayList<UserLocal> = context.database.use {
        val notes = ArrayList<UserLocal>()

        select("users", "id", "email", "username", "name", "surname", "location", "image", "eventsId",
            "password", "isModer", "phone", "vk", "facebook", "ok", "telegram", "skype")
            .parseList(object: MapRowParser<List<UserLocal>> {
                override fun parseRow(columns: Map<String, Any?>): List<UserLocal> {
                    val id = columns.getValue("id")
                    val email = columns.getValue("email")
                    val nickname = columns.getValue("username")
                    val name = columns.getValue("name")
                    val surname = columns.getValue("surname")
                    val location = columns.getValue("location")
                    val image = columns.getValue("image")
                    val eventsId = columns.getValue("eventsId")
                    val password = columns.getValue("password")
                    val isModer = columns.getValue("isModer")
                    val phone = columns.getValue("phone")
                    val vk = columns.getValue("vk")
                    val facebook = columns.getValue("facebook")
                    val ok = columns.getValue("ok")
                    val telegram = columns.getValue("telegram")
                    val skype = columns.getValue("skype")

                    val note = UserLocal(id.toString().toInt(), email.toString(), nickname.toString(),
                        name.toString(), surname.toString(), location.toString(), image.toString(),
                        DatabaseArrayConverter.convertToArray(eventsId.toString(), "userArray"),
                        password.toString(), isModer.toString().toInt(), phone.toString(), vk.toString(),
                        facebook.toString(), ok.toString(), telegram.toString(), skype.toString())
                    notes.add(note)

                    return notes
                }
            })
        notes
    }

    fun isUserValid(userNickname : String, userPassword : String) : Boolean {
        val users = findAll()
        return !users.filter { it.username == userNickname && it.password == userPassword}.isEmpty()
    }

    fun findUser(username: String) : UserLocal{
        return findAll().first {
            it.username == username
        }
    }

    fun findUser(id: Int): UserLocal{
        return findAll().first {
            it.id == id
        }
    }

    fun create(user: UserLocal) = context.database.use {
        insert("users",
            "id" to user.id,
            "email" to user.email,
            "username" to user.username,
            "name" to user.name,
            "surname" to user.surname,
            "location" to user.location,
            "image" to user.image,
            "events_id" to DatabaseArrayConverter.convertToString("user ${user.id}", user.eventsId),
            "password" to user.password,
            "isModer" to user.isModer,
            "phone" to user.phone,
            "vk" to user.vk,
            "facebook" to user.facebook,
            "ok" to user.ok,
            "telegram" to user.telegram,
            "skype" to user.skype
        )
    }

    fun update(user: UserLocal) = context.database.use {
        update("users",
            "id" to user.id,
            "email" to user.email,
            "username" to user.username,
            "name" to user.name,
            "surname" to user.surname,
            "location" to user.location,
            "image" to user.image,
            "events_id" to user.eventsId,
            "password" to user.password,
            "isModer" to user.isModer,
            "phone" to user.phone,
            "vk" to user.vk,
            "facebook" to user.facebook,
            "ok" to user.ok,
            "telegram" to user.telegram,
            "skype" to user.skype)
            .whereArgs("id = {userId}", "userId" to user.id)
            .exec()
    }

    fun delete(user: UserLocal) = context.database.use{
        delete("users", whereClause = "id = {userId}", args = *arrayOf("userId" to user.id))
    }
}