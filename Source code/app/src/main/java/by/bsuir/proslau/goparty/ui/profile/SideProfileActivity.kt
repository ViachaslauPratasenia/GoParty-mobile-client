package by.bsuir.proslau.goparty.ui.profile

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.entity.local.UserLocal
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_side_profile.*

class SideProfileActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side_profile)

        val currentUser = intent.getSerializableExtra("currentUser") as UserLocal

        Glide.with(this)
            .asBitmap()
            .load(currentUser.image + "/${currentUser.username}.jpg")
            .into(side_profile_avatar)

        side_profile_name.text = "${currentUser.name} ${currentUser.surname}"

        setField(side_profile_location, currentUser.location)
        setField(side_profile_email, currentUser.email)
        setField(side_profile_phone, currentUser.phone)
        setField(side_profile_vk, currentUser.vk)
        setField(side_profile_facebook, currentUser.facebook)
        setField(side_profile_ok, currentUser.ok)
        setField(side_profile_skype, currentUser.skype)
        setField(side_profile_telegram, currentUser.telegram)

        side_profile_back.setOnClickListener {
            finish()
        }
    }

    private fun setField(textView: TextView, string: String){
        if(string.isEmpty()){
            textView.text = getString(R.string.no_social)
        } else {
            textView.text = string
        }
    }
}