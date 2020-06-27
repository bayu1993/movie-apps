package com.example.movieapps.presentation.detail.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapps.R
import com.example.movieapps.data.model.Genre
import com.example.movieapps.data.model.UserReview
import com.example.movieapps.data.response.Videos
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.item_movie_review.view.*
import kotlinx.android.synthetic.main.item_genres.view.*

class MovieReviewAdapter : RecyclerView.Adapter<MovieReviewAdapter.ViewHolder>() {

    private val listTrailer = mutableListOf<UserReview>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie_review, parent, false))
    }

    override fun getItemCount(): Int {
        return listTrailer.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listTrailer[position])
    }

    fun setData(data: List<UserReview>) {
        listTrailer.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: UserReview) {
            itemView.tv_author.text = data.author
            itemView.tv_content.text = data.content
        }
    }
}