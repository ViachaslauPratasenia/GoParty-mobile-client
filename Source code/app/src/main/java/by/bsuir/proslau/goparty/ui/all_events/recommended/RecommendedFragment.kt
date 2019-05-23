package by.bsuir.proslau.goparty.ui.all_events.recommended

import android.app.Activity.RESULT_OK
import android.content.ContentValues.TAG
import android.content.Context
import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import by.bsuir.proslau.goparty.App
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.db.base.addEvents
import by.bsuir.proslau.goparty.db.local.EventRepository
import by.bsuir.proslau.goparty.ui.all_events.AddEventActivity
import by.bsuir.proslau.goparty.entity.local.EventLocal
import by.bsuir.proslau.goparty.logic.events.EventLogicManager
import by.bsuir.proslau.goparty.logic.local.events.EventLocalManager
import by.bsuir.proslau.goparty.ui.all_events.EventRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_recommended.view.*

class RecommendedFragment : Fragment() {
    internal lateinit var view: View
    //private val eventManager = EventLogicManager()
    lateinit var adapter: EventRecyclerViewAdapter
    var events: List<EventLocal> = ArrayList()
    var page = 1

    val REQUEST_ADD_EVENT = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_recommended, container, false)
        view.recommended_fab.setOnClickListener {
            val intent = Intent(view.context, AddEventActivity::class.java)
            startActivityForResult(intent, REQUEST_ADD_EVENT)
        }

        Toast.makeText(App.context, "Check your internet connection", Toast.LENGTH_SHORT).show()

        if (page == 1) {
            initPage()
            initRecommendedRecyclerView()
            events = EventLocalManager.getEventsByPage(page)
            adapter.setData(events)
        }

        view.prev_button_rec.setOnClickListener {
            if (page > 1) {
                page--
                events = EventLocalManager.getEventsByPage(page)
                adapter.setData(events)
                initPage()
            }
        }

        view.next_button_rec.setOnClickListener {
            page++
            events = EventLocalManager.getEventsByPage(page)
            if(!events.isEmpty()){

                adapter.setData(events)
                initPage()
            }

        }

        return view
    }

    private fun initRecommendedRecyclerView() {
        adapter = EventRecyclerViewAdapter(
            events as java.util.ArrayList<EventLocal>,
            context!!
        )
        //val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_recommended)
        view.recycler_recommended.adapter = adapter
        view.recycler_recommended.layoutManager = LinearLayoutManager(context)
    }

    private fun initPage() {
        view.page_number.text = page.toString()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_ADD_EVENT) {
            if (resultCode == RESULT_OK) {
                val isEventAdded = data!!.getBooleanExtra("isEventAdded", false)
                if (isEventAdded) {
                    events = EventLocalManager.getEventsByPage(page)
                    adapter.setData(events)
                }
            }
        }
    }

    fun updateView(){
        events = EventLocalManager.getEventsByPage(page)
        adapter.setData(events)
        initPage()
    }
}