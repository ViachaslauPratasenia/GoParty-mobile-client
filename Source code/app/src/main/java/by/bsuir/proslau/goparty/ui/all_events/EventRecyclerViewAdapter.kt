package by.bsuir.proslau.goparty.ui.all_events

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.entity.Event
import by.bsuir.proslau.goparty.entity.authorization.ShortUser
import by.bsuir.proslau.goparty.logic.profile.ProfileForEventsImpl
import by.bsuir.proslau.goparty.utils.Base64Converter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class EventRecyclerViewAdapter(private var eventList: ArrayList<Event>, private val context: Context) :
    RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.event_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val currentItem = eventList[i]
        val currentUser = currentItem.user
        if (currentUser != null) {
            /*Glide.with(context)
                .asBitmap()
                .load(currentUser.avatar)
                .into(viewHolder.profileAvatar)*/
            viewHolder.profileAvatar.setImageBitmap(Base64Converter.convertToBitmap(currentUser.image))
            viewHolder.profileName.text = currentUser.username
        }
        viewHolder.eventTitle.text = currentItem.title
        viewHolder.eventDate.text = currentItem.startTime
        val location = "${currentItem.location!!.city.toString()}, ${currentItem.location!!.country.toString()}"
        viewHolder.eventLocation.text = location
        /*Glide.with(context)
            .asBitmap()
            .load(currentItem.photoURL)
            .into(viewHolder.eventPhoto)*/
        viewHolder.eventPhoto.setImageBitmap(Base64Converter.convertToBitmap(currentItem.photoURL))
        viewHolder.eventJoin.setOnClickListener {
            Toast.makeText(context, currentItem.title + " joined", Toast.LENGTH_SHORT).show()
        }
        viewHolder.eventOpen.setOnClickListener {
            if (currentUser != null) {
                startActivityWithData(currentUser, currentItem)
            }
        }
        viewHolder.constraintLayout.setOnClickListener {
            if (currentUser != null) {
                startActivityWithData(currentUser, currentItem)
            }
        }
    }

    private fun startActivityWithData(currentUser : ShortUser, currentEvent : Event ){
        val intent = Intent(context, EventDetailActivity::class.java)
        intent.putExtra("event", currentEvent)
        intent.putExtra("user", currentUser)
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    fun setData(list: List<Event>){
        eventList.clear()
        eventList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profileAvatar : CircleImageView = itemView.findViewById(R.id.event_profile_avatar)
        var profileName : TextView = itemView.findViewById(R.id.event_profile_name)
        var eventTitle : TextView = itemView.findViewById(R.id.event_title)
        var eventDate : TextView = itemView.findViewById(R.id.event_date)
        var eventLocation : TextView = itemView.findViewById(R.id.event_location)
        var eventPhoto : ImageView = itemView.findViewById(R.id.event_image)
        var eventJoin : Button = itemView.findViewById(R.id.btn_event_join)
        var eventOpen : Button = itemView.findViewById(R.id.btn_event_open)
        var constraintLayout: ConstraintLayout = itemView.findViewById(R.id.event_parent_layout)
    }
}