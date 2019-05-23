package by.bsuir.proslau.goparty.ui.sideusers

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import by.bsuir.proslau.goparty.App
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.entity.local.UserLocal
import by.bsuir.proslau.goparty.ui.profile.SideProfileActivity
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

class SideUserRVAdapter(private var userList: ArrayList<UserLocal>, private val context: Context) :
    RecyclerView.Adapter<SideUserRVAdapter.ViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.side_user_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val currentUser = userList[p1]
        Glide.with(context)
            .asBitmap()
            .load(currentUser.image + "/${currentUser.username}.jpg")
            .into(p0.sideUserAvatar)
        p0.sideUserUsername.text = "${currentUser.name} ${currentUser.surname}"
        p0.constraintLayout.setOnClickListener {
            Toast.makeText(context, "${currentUser.username} is called", Toast.LENGTH_SHORT).show()
            /*val intent = Intent(context, SideProfileActivity::class.java)
            intent.putExtra("currentUser", currentUser)
            context.startActivity(intent)*/
            startActivityWithData(currentUser)

        }
    }

    private fun startActivityWithData(currentUser: UserLocal){
        val intent = Intent(context, SideProfileActivity::class.java)
        intent.putExtra("currentUser", currentUser)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(intent)
    }

    fun setData(list: List<UserLocal>){
        userList.clear()
        userList.addAll(list)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var sideUserAvatar : CircleImageView = itemView.findViewById(R.id.side_user_avatar)
        var sideUserUsername : TextView = itemView.findViewById(R.id.side_user_username)
        var constraintLayout: ConstraintLayout = itemView.findViewById(R.id.side_user_item_layout)
    }
}