package com.example.todoapplication.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.R
import com.example.todoapplication.model.Notes



class RecycleAdaptor(var notesList: List<Notes>) :
    RecyclerView.Adapter<RecycleAdaptor.ViewHolder>() {

    var setOnItemClickListener: ((Notes) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val title: TextView = itemView.findViewById(R.id.title)
        val subtitle: TextView = itemView.findViewById(R.id.subtitle)
        val date: TextView = itemView.findViewById(R.id.datetext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.items_notes, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            title.text = notesList[position].title
            subtitle.text = notesList[position].subtitle
            date.text = notesList[position].date
            itemView.setOnClickListener {

                setOnItemClickListener?.invoke(notesList[position])
            }
        }

    }


}

