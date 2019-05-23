package by.bsuir.proslau.goparty.ui.all_events

import android.os.Bundle
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.Toolbar
import android.view.View
import by.bsuir.proslau.goparty.App.Companion.context
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.db.local.EventRepository
import by.bsuir.proslau.goparty.entity.local.EventLocal
import by.bsuir.proslau.goparty.logic.categories.CategoriesLogicManager
import kotlinx.android.synthetic.main.activity_events.*

class EventByTagsActivity: AppCompatActivity() {
    lateinit var adapter: EventRecyclerViewAdapter
    var events: List<EventLocal> = ArrayList()
    private val categoryManager = CategoriesLogicManager()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)

        val code = intent.getIntExtra("tagCode", 0)

        val toolbar = findViewById<View>(R.id.event_by_tag_toolbar) as Toolbar
        toolbar.title = categoryManager.getByCode(code).title
        setSupportActionBar(toolbar)
        val actionBar : ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.setDisplayShowHomeEnabled(true)

        initEventsByTagRecyclerView()


        events = EventRepository(context).getByTag(code)
        adapter.setData(events)
    }

    private fun initEventsByTagRecyclerView() {
        adapter = EventRecyclerViewAdapter(
            events as ArrayList<EventLocal>,
            context
        )
        recycler_events_by_tag.adapter = adapter
        recycler_events_by_tag.layoutManager = LinearLayoutManager(context)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}