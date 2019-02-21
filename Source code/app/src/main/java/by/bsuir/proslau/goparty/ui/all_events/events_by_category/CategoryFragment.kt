package by.bsuir.proslau.goparty.ui.all_events.events_by_category

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import by.bsuir.proslau.goparty.R
import by.bsuir.proslau.goparty.logic.categories.CategoryImpl.Companion.initCategories

class CategoryFragment : Fragment() {
    internal lateinit var view: View
    lateinit var adapter : CategoryRecyclerViewAdapter
    private var categories : List<Category> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        view = inflater.inflate(R.layout.fragment_events, container, false)
        initImageBitmaps()
        return view
    }

    private fun initImageBitmaps() {
        categories = initCategories()
        initCategoriesRecyclerView()
    }

    private fun initCategoriesRecyclerView() {
        adapter = CategoryRecyclerViewAdapter(
            categories as java.util.ArrayList<Category>,
            context!!
        )
        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_events)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)
    }
}