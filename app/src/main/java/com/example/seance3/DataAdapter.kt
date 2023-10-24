package com.example.seance3

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class DataViewHolder(itemView: View):
    ViewHolder(itemView) {

    val textView: TextView

    init {
        textView = itemView.findViewById(R.id.data_text_view)
    }



}


class DataAdapter(val context: Context): Adapter<DataViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.item_data, parent, false)
        return DataViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return 100
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.textView.text = "$position"
    }
}