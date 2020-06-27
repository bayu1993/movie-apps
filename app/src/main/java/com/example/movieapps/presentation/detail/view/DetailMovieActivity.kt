package com.example.movieapps.presentation.detail.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapps.BuildConfig
import com.example.movieapps.R
import com.example.movieapps.data.response.MovieDetailResponse
import com.example.movieapps.data.response.UserReviewResponse
import com.example.movieapps.network.repo.MovieRepo
import com.example.movieapps.presentation.detail.adapter.GenreAdapter
import com.example.movieapps.presentation.detail.adapter.MovieReviewAdapter
import com.example.movieapps.presentation.detail.presenter.DetailMoviePresenter
import com.example.movieapps.presentation.genre.view.MovieView
import com.example.movieapps.utils.EndlessScrollListener
import com.example.movieapps.utils.displayDate
import com.example.movieapps.utils.gone
import com.example.movieapps.utils.visible
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_movie.*

class DetailMovieActivity : AppCompatActivity(), MovieView<MovieDetailResponse>, DetailView {

    private var id: Int = 0
    private lateinit var presenter: DetailMoviePresenter
    private lateinit var adapter: GenreAdapter
    private lateinit var movieReviewAdapter: MovieReviewAdapter
    private var pages = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movie)

        if (intent.hasExtra(EXTRA_MOVIE_ID)) {
            id = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
            presenter = DetailMoviePresenter()
            presenter.getMovieDetail(id, MovieRepo(), this)
            presenter.getMovieReview(id, pages, MovieRepo(), this)
        } else finish()

        adapter = GenreAdapter()
        rv_genre.setHasFixedSize(true)
        rv_genre.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_genre.adapter = adapter

        movieReviewAdapter = MovieReviewAdapter()
        rv_review.setHasFixedSize(true)
        rv_review.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rv_review.adapter = movieReviewAdapter
        rv_review.addOnScrollListener(scrollData())

    }

    override fun onHide() {
        progress_movie_detail.visible()
        card_1.gone()
        card_2.gone()
        card_3.gone()
    }

    override fun onShow() {
        progress_movie_detail.gone()
        card_1.visible()
        card_2.visible()
        card_3.visible()
    }

    override fun onError(error: Throwable) {
        Log.d("movieapps", "detail movie error: ${Gson().toJsonTree(error)}")
        Snackbar.make(ypv_video, "Error : ${error.localizedMessage}", Snackbar.LENGTH_INDEFINITE).setAction("Retry") {
            presenter.getMovieDetail(id, MovieRepo(), this)
            presenter.getMovieReview(id, 1, MovieRepo(), this)
        }.show()
    }

    override fun onSuccess(data: MovieDetailResponse) {
        adapter.setData(data.genres)
        ypv_video.initializeWithWebUi(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                data.videos.results[0].key?.let { youTubePlayer.cueVideo(it, 0F) }
            }
        }, true)
        tv_give_rate.setOnClickListener {
            Snackbar.make(it, "Sorry, this feature is not ready", Snackbar.LENGTH_SHORT).show()
        }
        tv_title_detail.text = data.title
        tv_lang.text = "${data.original_language}"
        tv_overview.text = data.overview
        tv_popularity_detail.text = data.popularity.toString()
        tv_release_date_detail.text = data.release_date?.displayDate()
        tv_rate_detail.text = data.vote_count.toString()
        tv_rating_detail.text = "${data.vote_average}/10"
        Picasso.get().load("${BuildConfig.IMAGE_BASE_URL}${data.poster_path}").into(iv_poster)

        release_date_details.text = data.release_date?.displayDate()
        var spokenLanguage = ""
        data.spoken_languages.forEachIndexed { index, spoken ->
            spokenLanguage += if (index == data.spoken_languages.lastIndex) spoken.name else "${spoken.name}, "
        }
        var countries = ""
        data.production_countries.forEachIndexed { index, country ->
            countries += if (index == data.production_countries.lastIndex) country.name else "${country.name}, "
        }
        var companies = ""
        data.production_companies.forEachIndexed { index, company ->
            companies += if (index == data.production_companies.lastIndex) company.name else "${company.name}, "
        }

        tv_spoken.text = spokenLanguage
        tv_country.text = countries
        tv_company.text = companies


        Log.d("movieapps", "detail movie video: ${Gson().toJsonTree(data.videos)}")
        Log.d("movieapps", "detail movie data: ${Gson().toJsonTree(data)}")
    }

    override fun onSuccess(data: UserReviewResponse) {
        if (data.results.isEmpty()) card_2.gone() else card_2.visible()
        movieReviewAdapter.setData(data.results)
        Log.d("movieapps", "detail movie data review: ${Gson().toJsonTree(data)}")
    }

    private fun scrollData() = object : EndlessScrollListener() {
        override fun onloadMore() {
            pages++
            presenter.getMovieReview(id, pages, MovieRepo(), this@DetailMovieActivity)
        }

    }


    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
    }
}
