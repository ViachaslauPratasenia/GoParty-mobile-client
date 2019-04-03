package by.bsuir.proslau.goparty.ui.all_events.recommended

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.logic.events.EventLogicManagerOld
import by.bsuir.proslau.goparty.ui.all_events.AddEventActivity
import by.bsuir.proslau.goparty.ui.all_events.Event
import by.bsuir.proslau.goparty.ui.all_events.EventRecyclerViewAdapter

class RecommendedFragment : Fragment() {
    internal lateinit var view: View
    private val eventManager = EventLogicManagerOld()
    lateinit var adapter : EventRecyclerViewAdapter
    private var events : List<Event> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_recommended, container, false)
        val fab = view.findViewById<View>(R.id.recommended_fab)
        fab.setOnClickListener {
            val intent = Intent(view.context, AddEventActivity::class.java)
            startActivity(intent)
        }
        initImageBitmaps()
        return view
    }

    private fun initImageBitmaps() {
        events = eventManager.getAll()
        initCategoriesRecyclerView()
    }

    private fun initCategoriesRecyclerView() {
        adapter = EventRecyclerViewAdapter(
            events as java.util.ArrayList<Event>,
            context!!
        )
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_recommended)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}