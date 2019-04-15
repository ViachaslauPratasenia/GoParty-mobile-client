package by.bsuir.proslau.goparty.ui.all_events.recommended

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.ui.all_events.AddEventActivity
import by.bsuir.proslau.goparty.entity.Event
import by.bsuir.proslau.goparty.logic.events.EventLogicManager
import by.bsuir.proslau.goparty.ui.all_events.EventRecyclerViewAdapter
import kotlinx.android.synthetic.main.fragment_recommended.view.*

class RecommendedFragment : Fragment() {
    internal lateinit var view: View
    private val eventManager = EventLogicManager(context!!)
    lateinit var adapter : EventRecyclerViewAdapter
    private var events : List<Event> = ArrayList()

    private var page = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_recommended, container, false)
        view.recommended_fab.setOnClickListener {
            val intent = Intent(view.context, AddEventActivity::class.java)
            startActivity(intent)
        }

        if(page == 1){
            initImageBitmaps()
        }

        view.prev_button_rec.setOnClickListener {
            if(page > 1){
                page--
                initImageBitmaps()
            }
        }

        view.next_button_rec.setOnClickListener {
            page++
            initImageBitmaps()
        }

        return view
    }

    private fun initImageBitmaps() {
        events = eventManager.getEventByPage(context!!, page)
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
}