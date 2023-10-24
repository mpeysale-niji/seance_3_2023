package com.example.seance3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class CategoryViewHolder(itemView: View):
    ViewHolder(itemView) {

    val titleTextView: TextView
    val descriptionTextView: TextView

    init {
        titleTextView = itemView.findViewById(R.id.category_title_textview)
        descriptionTextView = itemView.findViewById(R.id.category_description_textview)
    }
}


class CategoryAdapter(val context: Context,
                      val categories: List<Category>): Adapter<CategoryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_data, parent, false)
        return CategoryViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return categories.count()
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.titleTextView.text = category.title
        holder.descriptionTextView.text = category.description
    }
}