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
import by.bsuir.proslau.goparty.App
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.db.local.LocalStore
import by.bsuir.proslau.goparty.db.local.UserRepository
import kotlinx.android.synthetic.main.activity_profile_edit.*

class ProfileEditActivity : AppCompatActivity() {

    var code : Int = 0

    private val localStore = LocalStore()
    val user = UserRepository(App.context).findUser(localStore.userId!!)

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

        val toolbar = findViewById<View>(R.id.profile_edit_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionBar : ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        code = intent.getIntExtra("code", 0)



        when(code){
            LOCATION_CODE -> {
                image.setBackgroundResource(R.drawable.location_icon)
                profile_edit_text.hint = "Location"
                profile_edit_text.setText(user.location)
                profile_edit_text.inputType = InputType.TYPE_CLASS_TEXT
            }
            EMAIL_CODE -> {
                image.setBackgroundResource(R.drawable.email_icon)
                profile_edit_text.hint = "Email"
                profile_edit_text.setText(user.email)
                profile_edit_text.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
            }
            PHONE_CODE -> {
                image.setBackgroundResource(R.drawable.phone_icon)
                profile_edit_text.hint = "Phone number"
                profile_edit_text.setText(user.phone)
                profile_edit_text.inputType = InputType.TYPE_CLASS_PHONE
            }
            VK_CODE -> {
                image.setBackgroundResource(R.drawable.vk_icon)
                profile_edit_text.hint = "VK"
                profile_edit_text.setText(user.vk)
                profile_edit_text.inputType = InputType.TYPE_CLASS_TEXT
            }
            FACEBOOK_CODE -> {
                image.setBackgroundResource(R.drawable.facebook_icon)
                profile_edit_text.hint = "Facebook"
                profile_edit_text.setText(user.facebook)
                profile_edit_text.inputType = InputType.TYPE_CLASS_TEXT
            }
            ODNOKL_CODE -> {
                image.setBackgroundResource(R.drawable.odno_icon)
                profile_edit_text.hint = "Odnoklassniki"
                profile_edit_text.setText(user.ok)
                profile_edit_text.inputType = InputType.TYPE_CLASS_TEXT
            }
            TELEGRAM_CODE -> {
                image.setBackgroundResource(R.drawable.telegram_icon)
                profile_edit_text.hint = "Telegram"
                profile_edit_text.setText(user.telegram)
                profile_edit_text.inputType = InputType.TYPE_CLASS_TEXT
            }
            SKYPE_CODE -> {
                image.setBackgroundResource(R.drawable.skype_icon)
                profile_edit_text.hint = "Skype"
                profile_edit_text.setText(user.skype)
                profile_edit_text.inputType = InputType.TYPE_CLASS_TEXT
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
                when(code){
                    LOCATION_CODE -> {
                        user.location = profile_edit_text.text.toString()
                    }
                    EMAIL_CODE -> {
                        user.email = profile_edit_text.text.toString()
                    }
                    PHONE_CODE -> {
                        user.phone = profile_edit_text.text.toString()
                    }
                    VK_CODE -> {
                        user.vk = profile_edit_text.text.toString()
                    }
                    FACEBOOK_CODE -> {
                        user.facebook = profile_edit_text.text.toString()
                    }
                    ODNOKL_CODE -> {
                        user.ok = profile_edit_text.text.toString()
                    }
                    TELEGRAM_CODE -> {
                        user.telegram = profile_edit_text.text.toString()
                    }
                    SKYPE_CODE -> {
                        user.skype = profile_edit_text.text.toString()
                    }
                }
                UserRepository(App.context).update(user)
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
