package by.bsuir.proslau.goparty.ui.all_events.joined

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.db.local.EventRepository
import by.bsuir.proslau.goparty.db.local.LocalStore
import by.bsuir.proslau.goparty.db.local.UserRepository
import by.bsuir.proslau.goparty.entity.local.EventLocal
import by.bsuir.proslau.goparty.entity.local.UserLocal
import by.bsuir.proslau.goparty.ui.all_events.EventRecyclerViewAdapter
import by.bsuir.proslau.goparty.utils.DatabaseArrayConverter
import kotlinx.android.synthetic.main.fragment_joined.view.*
import java.util.ArrayList

class JoinedFragment : Fragment() {
    lateinit var joinedView: View
    lateinit var adapter: EventRecyclerViewAdapter
    var events: List<EventLocal> = ArrayList()
    lateinit var user: UserLocal

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        joinedView = inflater.inflate(R.layout.fragment_joined, container, false)
        initRecommendedRecyclerView()

        Log.e("joined", "is called")

        return joinedView
    }

    private fun initRecommendedRecyclerView() {
        adapter = EventRecyclerViewAdapter(
            events as ArrayList<EventLocal>,
            context!!
        )
        //val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_recommended)
        joinedView.recycler_joined.adapter = adapter
        joinedView.recycler_joined.layoutManager = LinearLayoutManager(context)
    }

    fun updateView(){
        Log.e("Joined fragment", "updateView method")
        user = UserRepository(joinedView.context).findUser(LocalStore().userId!!)

        val eventsList = user.eventsId
        val newList: ArrayList<EventLocal> = ArrayList()
        eventsList.forEach {
            newList.add(EventRepository(joinedView.context).findById(it))
        }
        events = newList
        adapter.setData(events)
    }
}