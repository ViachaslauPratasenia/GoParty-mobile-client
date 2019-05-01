package by.bsuir.proslau.goparty.utils

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream
import android.graphics.BitmapFactory
import android.widget.ImageView


class Base64Converter {
    companion object {
        fun convertToBase64(bitmap: Bitmap): String{
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 75, baos)
            val imageBytes = baos.toByteArray()
            return Base64.encodeToString(imageBytes, Base64.DEFAULT)
        }

        fun convertToBitmap(string: String): Bitmap{
            val imageBytes = Base64.decode(string, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        }
    }
}