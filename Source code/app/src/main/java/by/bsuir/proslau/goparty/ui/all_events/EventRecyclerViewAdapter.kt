package by.bsuir.proslau.goparty.ui.all_events

import android.content.Intent
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.db.local.EventRepository
import by.bsuir.proslau.goparty.db.local.UserRepository
import by.bsuir.proslau.goparty.entity.Event
import by.bsuir.proslau.goparty.entity.authorization.ShortUser
import by.bsuir.proslau.goparty.entity.local.EventLocal
import by.bsuir.proslau.goparty.entity.local.UserLocal
import by.bsuir.proslau.goparty.utils.Base64Converter
import by.bsuir.proslau.goparty.utils.StorageUtils
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import java.io.File

class EventRecyclerViewAdapter(private var eventList: ArrayList<EventLocal>, private val context: Context) :
    RecyclerView.Adapter<EventRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.event_item, viewGroup, false)
        return ViewHolder(view)
    }

    /*override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val currentItem = eventList[i]
        val currentUser = currentItem.user
        if (currentUser != null) {
            *//*Glide.with(context)
                .asBitmap()
                .load(currentUser.avatar)
                .into(viewHolder.profileAvatar)*//*
            viewHolder.profileAvatar.setImageBitmap(Base64Converter.convertToBitmap(currentUser.image))
            viewHolder.profileName.text = currentUser.username
        }
        viewHolder.eventTitle.text = currentItem.title
        viewHolder.eventDate.text = currentItem.startTime
        val location = "${currentItem.location!!.city}, ${currentItem.location!!.country.toString()}"
        viewHolder.eventLocation.text = location
        *//*Glide.with(context)
            .asBitmap()
            .load(currentItem.photoURL)
            .into(viewHolder.eventPhoto)*//*
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
    }*/

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val currentItem = eventList[i]
        val currentUser = UserRepository(context).findUser(currentItem.createdBy)
        Glide.with(context)
            .asBitmap()
            .load(currentUser.image + "/${currentUser.username}.jpg")
            .into(viewHolder.profileAvatar)
        //viewHolder.profileAvatar.setImageBitmap(Base64Converter.convertToBitmap(currentUser.image))
        /*viewHolder.profileAvatar.setImageBitmap(StorageUtils.loadImageFromStorage(currentUser.image,
            currentUser.username))*/
        viewHolder.profileName.text = currentUser.username
        viewHolder.eventTitle.text = currentItem.name
        viewHolder.eventDate.text = currentItem.startTime
        //val location = "${currentItem.location!!.city}, ${currentItem.location!!.country.toString()}"
        viewHolder.eventLocation.text = "${currentItem.location}, ${currentItem.address}"
        Glide.with(context)
            .asBitmap()
            .load(currentItem.image + "/${currentItem.name}.jpg")
            .into(viewHolder.eventPhoto)
        //viewHolder.eventPhoto.setImageBitmap(Base64Converter.convertToBitmap(currentItem.image))
        /*viewHolder.eventPhoto.setImageBitmap(StorageUtils.loadImageFromStorage(currentItem.image,
            currentItem.name))*/
        viewHolder.eventJoin.setOnClickListener {
            Toast.makeText(context, currentItem.name + " joined", Toast.LENGTH_SHORT).show()
        }
        viewHolder.eventOpen.setOnClickListener {
            startActivityWithData(currentUser, currentItem)
        }
        viewHolder.constraintLayout.setOnClickListener {
            startActivityWithData(currentUser, currentItem)
        }

        Log.e("adapter", "event ${currentItem.id} is displayed")
    }

    private fun startActivityWithData(currentUser : UserLocal, currentEvent : EventLocal ){
        val intent = Intent(context, EventDetailActivity::class.java)
        intent.putExtra("event", currentEvent)
        intent.putExtra("user", currentUser)
        context.startActivity(intent)
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    fun addEvent(event: EventLocal){
        eventList.add(event)
        notifyDataSetChanged()
    }

    fun setData(list: List<EventLocal>){
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