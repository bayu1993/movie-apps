package com.example.movieapps.presentation.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapps.BuildConfig
import com.example.movieapps.R
import com.example.movieapps.data.model.Movie
import com.example.movieapps.utils.displayDate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_movie.view.*

class MovieAdapter(private val callback: (Movie) -> Unit) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private val movieList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position], callback)
    }

    fun setData(data: MutableList<Movie>) {
        movieList.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: Movie, callback: (Movie) -> Unit) {
            itemView.tv_title.text = data.title
            itemView.tv_popularity.text = data.popularity.toString()
            itemView.tv_rating.text = data.vote_average.toString()
            itemView.tv_rating_count.text = data.vote_count.toString()
            itemView.tv_release_date.text = data.release_date.displayDate()

            Picasso.get().load("${BuildConfig.IMAGE_BASE_URL}${data.poster_path}").into(itemView.iv_item_movie)

            itemView.iv_more.setOnClickListener { callback(data) }

            itemView.setOnClickListener { callback(data) }
        }
    }
}