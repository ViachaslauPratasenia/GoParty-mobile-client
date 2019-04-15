package by.bsuir.proslau.goparty.ui.all_events

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.entity.Event
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_event_detail.*

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

        event_detail_title.text = event!!.title
        event_detail_date.text = event.date
        val location = "${event.city.toString()}, ${event.country.toString()}"
        event_detail_location.text = location
        event_detail_desc.text = event.description
        Glide.with(this)
            .asBitmap()
            .load(event.photoURL)
            .into(event_detail_image)
        Glide.with(this)
            .asBitmap()
            .load(user!!.avatar)
            .into(event_detail_org_avatar)
        event_detail_organ.text = user.username
        event_detail_quantity.text = event.eventSubcribers.toString()

        btn_event_detail_message.setOnClickListener {
            Toast.makeText(this, "Message button", Toast.LENGTH_SHORT).show()
        }
        btn_event_detail_join.setOnClickListener {
            Toast.makeText(this, "Join button", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
