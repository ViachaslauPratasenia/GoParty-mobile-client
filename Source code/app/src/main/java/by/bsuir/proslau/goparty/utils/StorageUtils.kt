package by.bsuir.proslau.goparty.utils

import android.content.Context
import android.graphics.Bitmap
import by.bsuir.proslau.goparty.App.Companion.context
import android.content.ContextWrapper
import android.graphics.BitmapFactory
import java.io.*


class StorageUtils {
    companion object {
        fun getUriFromResource(resId: Int): String{
            return "drawable://$resId"
        }

        fun saveToInternalStorage(bitmapImage: Bitmap, title: String): String {
            val cw = ContextWrapper(context)
            // path to /data/data/yourapp/app_data/imageDir
            val directory = cw.getDir("imageDir", Context.MODE_PRIVATE)
            // Create imageDir
            val mypath = File(directory, "$title.jpg")

            var fos: FileOutputStream? = null
            try {
                fos = FileOutputStream(mypath)
                // Use the compress method on the BitMap object to write image to the OutputStream
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    fos!!.close()
                } catch (e: IOException) {
                    e.printStackTrace()
                }

            }
            return directory.absolutePath
        }

        fun loadImageFromStorage(path: String, title: String): Bitmap? {

            try {
                val file = File(path, "$title.jpg")
                return BitmapFactory.decodeStream(FileInputStream(file))
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            }
            return null
        }
    }
}