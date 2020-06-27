package com.example.movieapps.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapps.R
import com.example.movieapps.data.model.Genre
import kotlinx.android.synthetic.main.item_genres.view.*

class GenreAdapter : RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    private val listTrailer = mutableListOf<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_genres, parent, false))
    }

    override fun getItemCount(): Int {
        return listTrailer.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTrailer[position])
    }

    fun setData(data: List<Genre>) {
        listTrailer.clear()
        listTrailer.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Genre) {
            itemView.tv_genre_item.text = data.name
        }
    }
}