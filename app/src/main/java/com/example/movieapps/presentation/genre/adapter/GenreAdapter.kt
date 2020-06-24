package com.example.movieapps.presentation.genre.adapter

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapps.R
import com.example.movieapps.data.model.Genre
import kotlinx.android.synthetic.main.item_genre.view.*
import kotlin.random.Random

class GenreAdapter(private val callback:(Genre)-> Unit) : RecyclerView.Adapter<GenreAdapter.ViewHolder>(){

    private val listGenre = mutableListOf<Genre>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_genre,parent,false))
    }

    override fun getItemCount(): Int {
        return listGenre.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listGenre[position], callback)
    }

    fun setData(data:MutableList<Genre>){
        listGenre.clear()
        listGenre.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val bgCard= mutableListOf("#111d5e","#c70039","#f37121","#2f2519")
        fun bind(genre: Genre, callback: (Genre) -> Unit){
            itemView.tv_genre.text = genre.name
            //itemView.card.setCardBackgroundColor(Color.parseColor(bgCard.random()))
            itemView.setOnClickListener { callback(genre) }
        }
    }

}