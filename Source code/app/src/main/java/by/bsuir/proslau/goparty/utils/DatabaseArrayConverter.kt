package by.bsuir.proslau.goparty.utils

import org.json.JSONObject
import kotlin.collections.ArrayList

class DatabaseArrayConverter {
    companion object {
        fun convertToString(title: String, list: ArrayList<Int>): String{
            val json: JSONObject = JSONObject()
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
    }
}