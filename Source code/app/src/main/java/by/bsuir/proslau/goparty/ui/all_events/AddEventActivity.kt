package by.bsuir.proslau.goparty.ui.all_events

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
import by.bsuir.proslau.goparty.db.local.EventRepository
import by.bsuir.proslau.goparty.db.local.LocalStore
import by.bsuir.proslau.goparty.db.local.UserRepository
import by.bsuir.proslau.goparty.entity.local.EventLocal
import by.bsuir.proslau.goparty.utils.StorageUtils
import kotlinx.android.synthetic.main.activity_add_event.*
import kotlinx.android.synthetic.main.activity_register.*
import java.io.File
import java.io.InputStream

class AddEventActivity : AppCompatActivity() {

    private val PICK_IMAGE_EVENT = 2
    val counter = 16
    val localStore = LocalStore()

    var isEventAdded: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        val eventsCounter = localStore.eventsCounter!! + counter
        val newEvent = EventLocal(
            eventsCounter + 1,
            LocalStore().userId!!, "", "", "",
            "", "", "", 0,
            0, ArrayList()
            )
        localStore.saveEventsCounter(eventsCounter)
        addEventBtnBack.setOnClickListener {
            val intent = Intent()
            intent.putExtra("isEventAdded", isEventAdded)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        addEventAddPhoto.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_EVENT)
        }

        addEventFinishBtn.setOnClickListener {
            if(fieldsIsEmpty()){
                Toast.makeText(App.context, "Please input all field and add photo", Toast.LENGTH_SHORT).show()
            } else {
                newEvent.name = addEventTitle.text.toString()
                newEvent.startTime = addEventDate.text.toString()
                newEvent.address = addEventLocation.text.toString()
                newEvent.location = UserRepository(App.context).findUser(LocalStore().userId!!).location
                newEvent.description = addEventDesc.text.toString()
                val bitmap = (addEventImageView.drawable as BitmapDrawable).bitmap
                newEvent.image = StorageUtils.saveToInternalStorage(
                    bitmap, newEvent.name)
                EventRepository(App.context).create(newEvent)
                Toast.makeText(this, "Event added", Toast.LENGTH_SHORT).show()

                isEventAdded = true
                val intent = Intent()
                intent.putExtra("isEventAdded", isEventAdded)
                setResult(Activity.RESULT_OK, intent)
                finish()

                finish()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        try {
            if (resultCode == Activity.RESULT_OK) {
                if (requestCode == PICK_IMAGE_EVENT) {
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
                    addEventImageView.setImageBitmap(selectedImage)
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
        if((addEventTitle.text.isEmpty()
                    || addEventDate.text.isEmpty()
                    || addEventDesc.text.isEmpty()
                    || addEventLocation.text.isEmpty())
        ){
            return true
        }
        return false
    }
}
