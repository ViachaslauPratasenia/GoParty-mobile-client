package by.bsuir.proslau.goparty.ui.all_events

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.widget.Toolbar
import android.view.View
import android.widget.Toast
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.db.local.EventRepository
import by.bsuir.proslau.goparty.db.local.LocalStore
import by.bsuir.proslau.goparty.db.local.UserRepository
import by.bsuir.proslau.goparty.entity.Event
import by.bsuir.proslau.goparty.entity.local.EventLocal
import by.bsuir.proslau.goparty.entity.local.UserLocal
import by.bsuir.proslau.goparty.ui.profile.SideProfileActivity
import by.bsuir.proslau.goparty.ui.sideusers.SideUserActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_event_detail.*

class EventDetailActivity : AppCompatActivity() {
    lateinit var user: UserLocal
    lateinit var event: EventLocal

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_event_detail)

        val toolbar = findViewById<View>(R.id.event_detail_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionBar : ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        user = (intent.getSerializableExtra("user") as? UserLocal)!!
        event = (intent.getSerializableExtra("event") as? EventLocal)!!

        bindEvent()

        event_detail_quantity_layout.setOnClickListener {
            Toast.makeText(this, "side user is called", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SideUserActivity::class.java)
            intent.putExtra("currentEvent", event)
            startActivity(intent)
        }

        btn_event_detail_message.setOnClickListener {
            //Toast.makeText(this, "Message button", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, SideProfileActivity::class.java)
            intent.putExtra("currentUser", user)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
        btn_event_detail_join.setOnClickListener {
            onJoinClicked(event)
        }
    }

    private fun bindEvent(){
        event_detail_title.text = event.name
        event_detail_date.text = event.startTime
        val location = "${event.address} ${event.location}"
        event_detail_location.text = location
        event_detail_desc.text = event.description
        Glide.with(this)
            .asBitmap()
            .load(event.image + "/${event.name}.jpg")
            .into(event_detail_image)
        Glide.with(this)
            .asBitmap()
            .load(user.image + "/${user.username}.jpg")
            .into(event_detail_org_avatar)
        event_detail_organ.text = user.username
        event_detail_quantity.text = event.eventSubscribers.toString()
        if(isUserJoined(event)){
            btn_event_detail_join.text = getString(R.string.leave_event)
        } else{
            btn_event_detail_join.text = getString(R.string.join_event)
        }
    }

    private fun onJoinClicked(event: EventLocal){
        val userApp = UserRepository(this).findUser(LocalStore().userId!!)
        if(isUserJoined(event)){
            userApp.eventsId.remove(event.id)
            event.eventSubscribers--
            event.subscribersId.remove(userApp.id)
            UserRepository(this).update(userApp)
            EventRepository(this).update(event)
            bindEvent()
            Toast.makeText(this, getString(R.string.sub_canceled), Toast.LENGTH_SHORT).show()
        } else{
            userApp.eventsId.add(event.id)
            event.eventSubscribers++
            event.subscribersId.add(userApp.id)
            UserRepository(this).update(userApp)
            EventRepository(this).update(event)
            bindEvent()
            Toast.makeText(this, getString(R.string.sub_issued), Toast.LENGTH_SHORT).show()
        }
    }

    private fun isUserJoined(event: EventLocal): Boolean{
        val userApp = UserRepository(this).findUser(LocalStore().userId!!)
        userApp.eventsId.forEach {
            if(it == event.id) return true
        }
        return false
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
