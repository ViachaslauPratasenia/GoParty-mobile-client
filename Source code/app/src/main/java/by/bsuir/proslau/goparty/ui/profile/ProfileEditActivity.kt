package by.bsuir.proslau.goparty.ui.profile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import by.bsuir.proslau.goparty.R

class ProfileEditActivity : AppCompatActivity() {

    var code : Int = 0

    private val LOCATION_CODE = 1
    private val EMAIL_CODE = 2
    private val PHONE_CODE = 3
    private val VK_CODE = 4
    private val FACEBOOK_CODE = 5
    private val ODNOKL_CODE = 6
    private val TELEGRAM_CODE = 7
    private val SKYPE_CODE = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_edit)

        val image = findViewById<View>(R.id.profile_edit_image) as ImageView
        val editText = findViewById<View>(R.id.profile_edit_text) as EditText

        val toolbar = findViewById<View>(R.id.profile_edit_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionBar : ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        code = intent.getIntExtra("code", 0)

        when(code){
            LOCATION_CODE -> {
                image.setBackgroundResource(R.drawable.location_icon)
                editText.hint = "Location"
                editText.inputType = InputType.TYPE_CLASS_TEXT
            }
            EMAIL_CODE -> {
                image.setBackgroundResource(R.drawable.email_icon)
                editText.hint = "Email"
                editText.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }
            PHONE_CODE -> {
                image.setBackgroundResource(R.drawable.phone_icon)
                editText.hint = "Phone number"
                editText.inputType = InputType.TYPE_CLASS_PHONE
            }
            VK_CODE -> {
                image.setBackgroundResource(R.drawable.vk_icon)
                editText.hint = "VK"
                editText.inputType = InputType.TYPE_CLASS_TEXT
            }
            FACEBOOK_CODE -> {
                image.setBackgroundResource(R.drawable.facebook_icon)
                editText.hint = "Facebook"
                editText.inputType = InputType.TYPE_CLASS_TEXT
            }
            ODNOKL_CODE -> {
                image.setBackgroundResource(R.drawable.odno_icon)
                editText.hint = "Odnoklassniki"
                editText.inputType = InputType.TYPE_CLASS_TEXT
            }
            TELEGRAM_CODE -> {
                image.setBackgroundResource(R.drawable.telegram_icon)
                editText.hint = "Telegram"
                editText.inputType = InputType.TYPE_CLASS_TEXT
            }
            SKYPE_CODE -> {
                image.setBackgroundResource(R.drawable.skype_icon)
                editText.hint = "Skype"
                editText.inputType = InputType.TYPE_CLASS_TEXT
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_setting_confirm, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_confirm -> {
                Toast.makeText(this, "Confirm data", Toast.LENGTH_SHORT).show()
                /*val intent = Intent()
                intent.putExtra("bio", user.bio)
                setResult(Activity.RESULT_OK, intent)*/
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
