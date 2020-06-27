package com.example.movieapps.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessScrollListener : RecyclerView.OnScrollListener(){
    private var mPreviousTotal = 0
    private var onLoading = true
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val itemVisibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager?.itemCount
        val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        if (onLoading){
            if (totalItemCount != null) {
                if (totalItemCount > mPreviousTotal){
                    onLoading= false
                    mPreviousTotal = totalItemCount
                }
            }
        }
        val visibleThreshold = 5
        if (totalItemCount != null) {
            if (!onLoading && totalItemCount - itemVisibleItemCount <= firstVisibleItem + visibleThreshold){
                onloadMore()
                onLoading = true
            }

        }
    }

    abstract fun onloadMore()
}