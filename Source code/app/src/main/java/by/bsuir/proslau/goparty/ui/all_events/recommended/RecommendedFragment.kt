package by.bsuir.proslau.goparty.ui.all_events.recommended

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
import android.widget.TextView
import android.widget.Toast
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.ui.all_events.AddEventActivity
import by.bsuir.proslau.goparty.entity.Event
import by.bsuir.proslau.goparty.logic.events.EventLogicManager
import by.bsuir.proslau.goparty.ui.all_events.EventRecyclerViewAdapter
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_recommended.view.*

class RecommendedFragment : Fragment() {
    internal lateinit var view: View
    private val eventManager = EventLogicManager()
    lateinit var adapter: EventRecyclerViewAdapter
    private var events: List<Event> = ArrayList()

    private var requestEvents: List<Event> = ArrayList()

    val compositeDisposable = CompositeDisposable()

    private var page = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_recommended, container, false)
        view.recommended_fab.setOnClickListener {
            val intent = Intent(view.context, AddEventActivity::class.java)
            startActivity(intent)
        }

        if (page == 1) {
            initPage()
            initImageBitmaps()
            /*val eventListTask = EventListTask()
            eventListTask.execute()*/
            //MyTask().execute()

        }

        view.prev_button_rec.setOnClickListener {
            if (page > 1) {
                page--
                /*initPage()
                initImageBitmaps()*/
                /*val eventListTask = EventListTask()
                eventListTask.execute()*/
            }
        }

        view.next_button_rec.setOnClickListener {
            page++
            /*initPage()
            initImageBitmaps()*/
        }

        return view
    }

    private fun initImageBitmaps() {
        //events = eventManager.getEventByPage(context!!, page)
        initCategoriesRecyclerView()
    }

    private fun initCategoriesRecyclerView() {
        adapter = EventRecyclerViewAdapter(
            events as java.util.ArrayList<Event>,
            context!!
        )
        //val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_recommended)
        view.recycler_recommended.adapter = adapter
        view.recycler_recommended.layoutManager = LinearLayoutManager(context)
    }

    private fun initPage() {
        view.page_number.text = page.toString()
    }


    /*internal inner class MyTask : AsyncTask<Void, String, List<Event>>() {

        override fun onPreExecute() {
            super.onPreExecute()
            Log.e(TAG, "onPreExecute")
        }

        override fun doInBackground(vararg params: Void?): List<Event>? {
            Log.e(TAG, "doInBackground")
            requestEvents = eventManager.getEventByPage(context!!, page)
            return requestEvents
        }

        override fun onPostExecute(result: List<Event>?) {
            Log.e(TAG, "onPostExecute")
            super.onPostExecute(result)
            if (result != null) {
                adapter.setData(result)
            }
        }
    }*/
}