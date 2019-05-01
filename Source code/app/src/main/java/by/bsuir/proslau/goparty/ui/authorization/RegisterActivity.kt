package by.bsuir.proslau.goparty.ui.authorization

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import by.bsuir.proslau.goparty.App
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.entity.local.UserLocal
import by.bsuir.proslau.goparty.utils.StorageUtils
import kotlinx.android.synthetic.main.activity_register.*
import java.io.File
import java.io.InputStream



class RegisterActivity : AppCompatActivity() {
    var photoPath = ""
    val PICK_IMAGE = 1

    companion object {
        var counter = 7
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val newUser = UserLocal(
            counter++, "", "", "", "", "", "", ArrayList(),
            "", 0, "", "", "", "", "", ""
        )

        register_image.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE)
        }

        btn_register_next.setOnClickListener {
            if(fieldsIsEmpty()){
                Toast.makeText(App.context, "Please input all field and add photo", Toast.LENGTH_SHORT).show()
            } else {
                newUser.email = inputRegisterEmail.text.toString()
                newUser.username = inputRegisterNickname.text.toString()
                newUser.name = inputRegisterName.text.toString()
                newUser.surname = inputRegisterSurname.text.toString()
                newUser.location = inputRegisterLocation.text.toString()
                val bitmap = (register_image.drawable as BitmapDrawable).bitmap
                newUser.image = StorageUtils.saveToInternalStorage(
                    bitmap,
                    newUser.username)
                val intent = Intent(this, PasswordActivity::class.java)
                intent.putExtra("newUser", newUser)
                startActivity(intent)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == PICK_IMAGE) {
                    var selectedImageUri = data!!.data
                    // Get the path from the Uri
                    val path = getPathFromURI(selectedImageUri)
                    if (path != null) {
                        val f = File(path)
                        selectedImageUri = Uri.fromFile(f)
                    }
                    //imageView.setImageURI(selectedImageUri)
                    val imageStream: InputStream = contentResolver.openInputStream(selectedImageUri)!!
                    val selectedImage: Bitmap = BitmapFactory.decodeStream(imageStream)
                    register_image.setImageBitmap(selectedImage)
                }
            }
        } catch (e: Exception) {
            Log.e("FileSelectorActivity", "File select error", e)
        }
    }

    fun getPathFromURI(contentUri: Uri?): String? {
        var res: String? = null
        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = contentResolver.query(contentUri!!, proj, null, null, null)
        if (cursor!!.moveToFirst()) {
            val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
            res = cursor.getString(column_index)
        }
        cursor.close()
        return res
    }

    fun fieldsIsEmpty(): Boolean{
        if((inputRegisterEmail.text.isEmpty()
                || inputRegisterNickname.text.isEmpty()
                || inputRegisterName.text.isEmpty()
                || inputRegisterSurname.text.isEmpty()
                || inputRegisterLocation.text.isEmpty())
        ){
            return true
        }
        return false
    }
}
