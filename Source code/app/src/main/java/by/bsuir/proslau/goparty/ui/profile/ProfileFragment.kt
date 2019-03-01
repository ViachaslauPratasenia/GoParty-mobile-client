package by.bsuir.proslau.goparty.ui.profile

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.bsuir.proslau.goparty.R

class ProfileFragment : Fragment() {

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

        locationView.setOnClickListener {
            Toast.makeText(profileView.context, "Location", Toast.LENGTH_SHORT).show()
        }
        emailView.setOnClickListener {
            Toast.makeText(profileView.context, "Email", Toast.LENGTH_SHORT).show()
        }
        phoneView.setOnClickListener {
            Toast.makeText(profileView.context, "Phone", Toast.LENGTH_SHORT).show()
        }
        vkView.setOnClickListener {
            Toast.makeText(profileView.context, "VK", Toast.LENGTH_SHORT).show()
        }
        facebookView.setOnClickListener {
            Toast.makeText(profileView.context, "Facebook", Toast.LENGTH_SHORT).show()
        }
        odnoklView.setOnClickListener {
            Toast.makeText(profileView.context, "Odnoklassniki", Toast.LENGTH_SHORT).show()
        }
        telegramView.setOnClickListener {
            Toast.makeText(profileView.context, "Telegram", Toast.LENGTH_SHORT).show()
        }
        skypeView.setOnClickListener {
            Toast.makeText(profileView.context, "Skype", Toast.LENGTH_SHORT).show()
        }

        return profileView
    }
}


