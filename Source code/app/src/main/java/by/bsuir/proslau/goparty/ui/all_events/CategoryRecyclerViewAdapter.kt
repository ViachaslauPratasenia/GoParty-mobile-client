package by.bsuir.proslau.goparty.ui.all_events

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import by.bsuir.proslau.goparty.R

import java.util.ArrayList

class CategoryRecyclerViewAdapter(private var categoriesList: ArrayList<Category>, private val context: Context) :
    RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.category_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val currentItem = categoriesList[i]
        viewHolder.categoryImage.setImageResource(currentItem.image)
        viewHolder.categoryTitle.text = currentItem.title
        viewHolder.constraintLayout.setOnClickListener {
            Toast.makeText(context, currentItem.title, Toast.LENGTH_SHORT).show()
        }
    }

    override fun getItemCount(): Int {
        return categoriesList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryImage: ImageView = itemView.findViewById(R.id.image_category)
        var categoryTitle: TextView = itemView.findViewById(R.id.title_category)
        var constraintLayout: ConstraintLayout = itemView.findViewById(R.id.event_parent_layout)
    }
}
