package by.bsuir.proslau.goparty.db.base

import android.app.Application
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.graphics.BitmapFactory
import by.bsuir.proslau.goparty.App
import by.bsuir.proslau.goparty.App.Companion.context
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.utils.DatabaseArrayConverter
import by.bsuir.proslau.goparty.utils.StorageUtils
import org.jetbrains.anko.db.*

class DBAnkoHelper (context: Context) : ManagedSQLiteOpenHelper(context, "v17", null, 1) {

    companion object {
        private var instance: DBAnkoHelper? = null

        @Synchronized
        fun getInstance(context: Context): DBAnkoHelper {
            if (instance == null){
                instance = DBAnkoHelper(context.applicationContext)
            }

            return instance!!
        }
    }

    override fun onCreate(database: SQLiteDatabase) {

        database.createTable("events", true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "createdBy" to INTEGER + NOT_NULL,
            "name" to TEXT + NOT_NULL,
            "address" to TEXT + NOT_NULL,
            "startTime" to TEXT + NOT_NULL,
            "location" to TEXT + NOT_NULL,
            "description" to TEXT,
            "image" to TEXT,
            "tags" to TEXT,
            "eventSubscribers" to TEXT,
            "subscribersId" to TEXT
            )

        database.createTable("users", true,
            "id" to INTEGER + PRIMARY_KEY + UNIQUE,
            "email" to TEXT + NOT_NULL,
            "username" to TEXT + NOT_NULL,
            "name" to TEXT + NOT_NULL,
            "surname" to TEXT + NOT_NULL,
            "location" to TEXT + NOT_NULL,
            "image" to TEXT,
            "eventsId" to TEXT,
            "password" to TEXT,
            "isModer" to INTEGER + NOT_NULL,
            "phone" to TEXT + NOT_NULL,
            "vk" to TEXT + NOT_NULL,
            "facebook" to TEXT + NOT_NULL,
            "ok" to TEXT + NOT_NULL,
            "telegram" to TEXT + NOT_NULL,
            "skype" to TEXT + NOT_NULL
            )

        database.insert("users",
            "id" to 1,
            "email" to "slavaprot14@gmail.com",
            "username" to "proslau",
            "name" to "Viachaslau",
            "surname" to "Pratasenia",
            "location" to "Minsk, Belarus",
            //"image" to context.resources.getString(R.string.myPhoto),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.me),
                "proslau"),
            "eventsID" to DatabaseArrayConverter.convertToString("user 1", arrayListOf(1,2,3,4,6,7,8,9,10,11,12)),
            "password" to "123",
            "isModer" to 1,
            "phone" to "+375445712877",
            "vk" to "proslau",
            "facebook" to "",
            "ok" to "",
            "telegram" to "t.me/proslau",
            "skype" to "slavaprot14"
            )

        database.insert("users",
            "id" to 2,
            "email" to "skatrr4@gmail.com",
            "username" to "vitaly",
            "name" to "Vitaliy",
            "surname" to "Pankov",
            "location" to "Minsk, Belarus",
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.vitaly),
                "vitaly"),
            "eventsID" to DatabaseArrayConverter.convertToString("user 2", arrayListOf(1,3,4,6,10,11,12)),
            "password" to "test",
            "isModer" to 0,
            "phone" to "+37544",
            "vk" to "pankov",
            "facebook" to "",
            "ok" to "",
            "telegram" to "t.me/pankov",
            "skype" to "vitaliy_pankov")

        database.insert("events",
            "id" to 1,
            "createdBy" to 1,
            "name" to "Football Match 1",
            "address" to "Vanda Metropolitano",
            "startTime" to "April 15, 19:00",
            "location" to "Madrid, Spain",
            "description" to "Description football match",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.wanda),
                "Football Match 1"
            ),
            "tags" to 3,
            "eventSubscribers" to 2,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 1", arrayListOf(1,2))
            )

        database.insert("events",
            "id" to 2,
            "createdBy" to 2,
            "name" to "Football Match 2",
            "address" to "Vanda Metropolitano",
            "startTime" to "May 24, 11:00",
            "location" to "Madrid, Spain",
            "description" to "Description football match",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.wanda),
                "Football Match 2"
            ),
            "tags" to 3,
            "eventSubscribers" to 1,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 2", arrayListOf(1))
        )

        //addUsers(App.context)
        //addEvents(App.context)
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        database.dropTable("events", ifExists = true)
        database.dropTable("users", ifExists = true)

    }
}

val Context.database: DBAnkoHelper
    get() = DBAnkoHelper.getInstance(applicationContext)