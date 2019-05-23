package by.bsuir.proslau.goparty.ui.sideusers

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import by.bsuir.proslau.goparty.App.Companion.context
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.db.local.UserRepository
import by.bsuir.proslau.goparty.entity.local.EventLocal
import by.bsuir.proslau.goparty.entity.local.UserLocal
import kotlinx.android.synthetic.main.activity_side_users.*

class SideUserActivity: AppCompatActivity() {
    lateinit var adapter: SideUserRVAdapter
    var users: List<UserLocal> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_side_users)

        val toolbar = findViewById<View>(R.id.side_user_toolbar) as Toolbar
        setSupportActionBar(toolbar)
        val actionBar : ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        initRecommendedRecyclerView()

        val currentEvent = intent.getSerializableExtra("currentEvent") as EventLocal
        val userList = currentEvent.subscribersId
        val tempList: ArrayList<UserLocal> = ArrayList()
        userList.forEach {
            tempList.add(UserRepository(this).findUser(it))
        }
        users = tempList

        adapter.setData(users)
    }

    private fun initRecommendedRecyclerView() {
        adapter = SideUserRVAdapter(
            users as ArrayList<UserLocal>,
            context
        )
        recycler_side_user.adapter = adapter
        recycler_side_user.layoutManager = LinearLayoutManager(context)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}