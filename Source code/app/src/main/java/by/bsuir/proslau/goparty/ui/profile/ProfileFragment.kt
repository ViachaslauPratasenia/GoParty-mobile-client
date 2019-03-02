package by.bsuir.proslau.goparty.ui.profile

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import by.bsuir.proslau.goparty.R

class ProfileFragment : Fragment() {

    private val LOCATION_CODE = 1
    private val EMAIL_CODE = 2
    private val PHONE_CODE = 3
    private val VK_CODE = 4
    private val FACEBOOK_CODE = 5
    private val ODNOKL_CODE = 6
    private val TELEGRAM_CODE = 7
    private val SKYPE_CODE = 8

    lateinit var editActivityIntent : Intent

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val profileView = inflater.inflate(R.layout.fragment_profile, container, false)

        val locationView = profileView.findViewById<View>(R.id.card_view_location) as CardView
        val emailView = profileView.findViewById<View>(R.id.card_view_email) as CardView
        val phoneView = profileView.findViewById<View>(R.id.card_view_phone) as CardView
        val vkView = profileView.findViewById<View>(R.id.card_view_vk) as CardView
        val facebookView = profileView.findViewById<View>(R.id.card_view_facebook) as CardView
        val odnoklView = profileView.findViewById<View>(R.id.card_view_odnokl) as CardView
        val telegramView = profileView.findViewById<View>(R.id.card_view_telegram) as CardView
        val skypeView = profileView.findViewById<View>(R.id.card_view_skype) as CardView
        val btnLogout = profileView.findViewById<View>(R.id.btn_logout) as Button

        //val profile = arguments!!.getString("profile")



        editActivityIntent = Intent(context, ProfileEditActivity::class.java)

        locationView.setOnClickListener {
            editActivityIntent.putExtra("code", LOCATION_CODE)
            startActivityForResult(editActivityIntent, LOCATION_CODE)
        }
        emailView.setOnClickListener {
            editActivityIntent.putExtra("code", EMAIL_CODE)
            startActivityForResult(editActivityIntent, EMAIL_CODE)
        }
        phoneView.setOnClickListener {
            editActivityIntent.putExtra("code", PHONE_CODE)
            startActivityForResult(editActivityIntent, PHONE_CODE)
        }
        vkView.setOnClickListener {
            editActivityIntent.putExtra("code", VK_CODE)
            startActivityForResult(editActivityIntent, VK_CODE)
        }
        facebookView.setOnClickListener {
            editActivityIntent.putExtra("code", FACEBOOK_CODE)
            startActivityForResult(editActivityIntent, FACEBOOK_CODE)
        }
        odnoklView.setOnClickListener {
            editActivityIntent.putExtra("code", ODNOKL_CODE)
            startActivityForResult(editActivityIntent, ODNOKL_CODE)
        }
        telegramView.setOnClickListener {
            editActivityIntent.putExtra("code", TELEGRAM_CODE)
            startActivityForResult(editActivityIntent, TELEGRAM_CODE)
        }
        skypeView.setOnClickListener {
            editActivityIntent.putExtra("code", SKYPE_CODE)
            startActivityForResult(editActivityIntent, SKYPE_CODE)
        }
        btnLogout.setOnClickListener {
            activity!!.finish()
        }

        return profileView
    }

    /*override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            EDIT_NAME_RESULT -> {
                if(resultCode == RESULT_OK){
                    val editName = data!!.getStringExtra("edit_name")
                    title.text = editName
                }
            }
            PHONE_RESULT -> {
                if(resultCode == RESULT_OK){
                    val phoneIntentData = data!!.getStringExtra("phone")
                    phone.text = phoneIntentData
                }
            }
            USERNAME_RESULT -> {
                if(resultCode == RESULT_OK){
                    val usernameIntentData = data!!.getStringExtra("username")
                    username.text = usernameIntentData
                }
            }
            BIO_RESULT -> {
                if(resultCode == RESULT_OK){
                    val bioIntentData = data!!.getStringExtra("bio")
                    bio.text = bioIntentData
                }
            }
        }

    }*/
}


