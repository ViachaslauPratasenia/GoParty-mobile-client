package by.bsuir.proslau.goparty.db.base

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.graphics.BitmapFactory
import by.bsuir.proslau.goparty.App.Companion.context
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.entity.local.EventLocal
import by.bsuir.proslau.goparty.entity.local.UserLocal
import by.bsuir.proslau.goparty.utils.DatabaseArrayConverter
import by.bsuir.proslau.goparty.utils.StorageUtils
import org.jetbrains.anko.db.*

class DBAnkoHelper (context: Context) : ManagedSQLiteOpenHelper(context, "v34", null, 1) {

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
            "id" to UserLocal.counter++,
            "email" to "slavaprot14@gmail.com",
            "username" to "proslau",
            "name" to "Viachaslau",
            "surname" to "Pratasenia",
            "location" to "Minsk, Belarus",
            //"image" to context.resources.getString(R.string.myPhoto),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.me),
                "proslau"),
            //"eventsId" to DatabaseArrayConverter.convertToString("user 1", arrayListOf(1,2,3,4,6,7,8,9,10,11,12)),
            "eventsId" to DatabaseArrayConverter.arrayToString(arrayListOf(1,2,3,4,6,7,8,9,10,11,12)),
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
            "id" to UserLocal.counter++,
            "email" to "skatrr4@gmail.com",
            "username" to "vitaly",
            "name" to "Vitaliy",
            "surname" to "Pankov",
            "location" to "Minsk, Belarus",
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.vitaly),
                "vitaly"),
            //"eventsId" to DatabaseArrayConverter.convertToString("user 2", arrayListOf(1,3,4,6,10,11,12)),
            "eventsId" to DatabaseArrayConverter.arrayToString(arrayListOf(1,3,4,6,10,11,12)),
            "password" to "test",
            "isModer" to 0,
            "phone" to "+37544",
            "vk" to "pankov",
            "facebook" to "",
            "ok" to "",
            "telegram" to "t.me/pankov",
            "skype" to "vitaliy_pankov")

        database.insert("events",
            "id" to EventLocal.counter++,
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
            "id" to EventLocal.counter++,
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

        database.insert("events",
            "id" to EventLocal.counter++,
            "createdBy" to 1,
            "name" to "Football Match 3",
            "address" to "Vanda Metropolitano",
            "startTime" to "August 1, 9:00",
            "location" to "Madrid, Spain",
            "description" to "Description football match",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.wanda),
                "Football Match 3"
            ),
            "tags" to 3,
            "eventSubscribers" to 2,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 3", arrayListOf(1,2))
        )

        database.insert("events",
            "id" to EventLocal.counter++,
            "createdBy" to 1,
            "name" to "Football Match 4",
            "address" to "Vanda Metropolitano",
            "startTime" to "August 5, 19:00",
            "location" to "Madrid, Spain",
            "description" to "Description football match",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.wanda),
                "Football Match 4"
            ),
            "tags" to 3,
            "eventSubscribers" to 2,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 4", arrayListOf(1,2))
        )

        database.insert("events",
            "id" to EventLocal.counter++,
            "createdBy" to 2,
            "name" to "Football Match 5",
            "address" to "Vanda Metropolitano",
            "startTime" to "August 6, 19:00",
            "location" to "Madrid, Spain",
            "description" to "Description football match",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.wanda),
                "Football Match 5"
            ),
            "tags" to 3,
            "eventSubscribers" to 0,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 5", arrayListOf())
        )

        database.insert("events",
            "id" to EventLocal.counter++,
            "createdBy" to 2,
            "name" to "Football Match 6",
            "address" to "Vanda Metropolitano",
            "startTime" to "August 9, 22:00",
            "location" to "Madrid, Spain",
            "description" to "Description football match",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.wanda),
                "Football Match 6"
            ),
            "tags" to 3,
            "eventSubscribers" to 2,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 6", arrayListOf(1,2))
        )

        database.insert("events",
            "id" to EventLocal.counter++,
            "createdBy" to 2,
            "name" to "Football Match 7",
            "address" to "Vanda Metropolitano",
            "startTime" to "August 12, 22:00",
            "location" to "Madrid, Spain",
            "description" to "Description football match",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.wanda),
                "Football Match 7"
            ),
            "tags" to 3,
            "eventSubscribers" to 1,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 7", arrayListOf(1))
        )

        database.insert("events",
            "id" to EventLocal.counter++,
            "createdBy" to 2,
            "name" to "Endgame 1",
            "address" to "Cinema Belarus",
            "startTime" to "April 28, 23:59",
            "location" to "Minsk, Belarus",
            "description" to "Description the avengers",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.endgame),
                "Endgame 1"
            ),
            "tags" to 2,
            "eventSubscribers" to 1,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 8", arrayListOf(2))
        )

        database.insert("events",
            "id" to EventLocal.counter++,
            "createdBy" to 1,
            "name" to "Endgame 2",
            "address" to "Cinema Aurora",
            "startTime" to "April 28, 23:59",
            "location" to "Minsk, Belarus",
            "description" to "Description the avengers",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.endgame),
                "Endgame 2"
            ),
            "tags" to 2,
            "eventSubscribers" to 2,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 9", arrayListOf(1,2))
        )

        database.insert("events",
            "id" to EventLocal.counter++,
            "createdBy" to 1,
            "name" to "Endgame 3",
            "address" to "Cinema Aurora",
            "startTime" to "April 29, 11:00",
            "location" to "Minsk, Belarus",
            "description" to "Description the avengers",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.endgame),
                "Endgame 3"
            ),
            "tags" to 2,
            "eventSubscribers" to 2,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 10", arrayListOf(1,2))
        )

        database.insert("events",
            "id" to EventLocal.counter++,
            "createdBy" to 1,
            "name" to "Endgame 4",
            "address" to "Cinema Belarus",
            "startTime" to "April 29, 17:00",
            "location" to "Minsk, Belarus",
            "description" to "Description the avengers",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.endgame),
                "Endgame 4"
            ),
            "tags" to 2,
            "eventSubscribers" to 1,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 11", arrayListOf(1))
        )

        database.insert("events",
            "id" to EventLocal.counter++,
            "createdBy" to 1,
            "name" to "Endgame 5",
            "address" to "Cinema Belarus",
            "startTime" to "April 29, 19:00",
            "location" to "Minsk, Belarus",
            "description" to "Description the avengers",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.endgame),
                "Endgame 5"
            ),
            "tags" to 2,
            "eventSubscribers" to 1,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 12", arrayListOf(1))
        )

        database.insert("events",
            "id" to EventLocal.counter++,
            "createdBy" to 1,
            "name" to "Sasha Solodukha 1",
            "address" to "Dynamo",
            "startTime" to "July 24, 18:00",
            "location" to "Minsk, Belarus",
            "description" to "Description the avengers",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.sasha),
                "Sasha Solodukha 1"
            ),
            "tags" to 4,
            "eventSubscribers" to 2,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 13", arrayListOf(1,2))
        )

        database.insert("events",
            "id" to EventLocal.counter++,
            "createdBy" to 2,
            "name" to "Sasha Solodukha 2",
            "address" to "Dynamo",
            "startTime" to "July 28, 18:00",
            "location" to "Minsk, Belarus",
            "description" to "Description the avengers",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.sasha),
                "Sasha Solodukha 2"
            ),
            "tags" to 4,
            "eventSubscribers" to 2,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 14", arrayListOf(1,2))
        )

        database.insert("events",
            "id" to EventLocal.counter++,
            "createdBy" to 2,
            "name" to "Sasha Solodukha 3",
            "address" to "Dynamo",
            "startTime" to "August 25, 18:00",
            "location" to "Minsk, Belarus",
            "description" to "Description the avengers",
            //"image" to context.resources.getString(R.string.vanda),
            //"image" to context.resources.getString(R.string.endgame),
            "image" to StorageUtils.saveToInternalStorage(
                BitmapFactory.decodeResource(context.resources, R.drawable.sasha),
                "Sasha Solodukha 3"
            ),
            "tags" to 4,
            "eventSubscribers" to 2,
            "subscribersId" to DatabaseArrayConverter.convertToString("event 15", arrayListOf(1,2))
        )
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        database.dropTable("events", ifExists = true)
        database.dropTable("users", ifExists = true)

    }
}

val Context.database: DBAnkoHelper
    get() = DBAnkoHelper.getInstance(applicationContext)