package by.bsuir.proslau.goparty.ui.all_events

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import by.bsuir.proslau.goparty.R
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class EventDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        val toolbar = findViewById<View>(R.id.event_detail_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionBar : ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        val user = intent.getSerializableExtra("user") as? ProfileForEvents
        val event = intent.getSerializableExtra("event") as? Event

        val eventTitle = findViewById<View>(R.id.event_detail_title) as TextView
        eventTitle.text = event!!.title
        val eventDate = findViewById<View>(R.id.event_detail_date) as TextView
        eventDate.text = event.date
        val eventLocation = findViewById<View>(R.id.event_detail_location) as TextView
        eventLocation.text = event.location
        val desc = findViewById<View>(R.id.event_detail_desc) as TextView
        desc.text = event.description
        val eventPhoto = findViewById<View>(R.id.event_detail_image) as ImageView
        Glide.with(this)
            .asBitmap()
            .load(event.photo)
            .into(eventPhoto)
        val userAvatar = findViewById<View>(R.id.event_detail_org_avatar) as CircleImageView
        Glide.with(this)
            .asBitmap()
            .load(user!!.avatar)
            .into(userAvatar)
        val username = findViewById<View>(R.id.event_detail_organ) as TextView
        username.text = user.username
        val quantity = findViewById<View>(R.id.event_detail_quantity) as TextView
        quantity.text = event.quantityJoined.toString()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
