package by.bsuir.proslau.goparty.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONObject
import kotlin.collections.ArrayList


class DatabaseArrayConverter {
    companion object {
        fun convertToString(title: String, list: ArrayList<Int>): String{
            val json = JSONObject()
            json.put(title, list)
            return json.toString()
        }
        fun convertToArray(stringFromDB: String, title: String): ArrayList<Int> {
            val json = JSONObject(stringFromDB)
            val jArray = json.optJSONArray(title)
            val list = ArrayList<Int>()
            if (jArray != null) {
                for (i in 0 until jArray.length()) {
                    list.add(jArray.getString(i).toInt())
                }
            }
            return list
        }

        fun arrayToString(list: ArrayList<Int>): String{
            return list.toString()
        }

        fun jsonToArray(string: String): ArrayList<Int> {
            val gson = Gson()
            val ints = gson.fromJson(string, IntArray::class.java)
            val list = ArrayList<Int>()
            ints.forEach {
                list.add(it)
            }
            return list
        }


    }
}