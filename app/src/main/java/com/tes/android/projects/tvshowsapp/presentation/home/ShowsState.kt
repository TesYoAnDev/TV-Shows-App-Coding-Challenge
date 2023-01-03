package com.tes.android.projects.tvshowsapp.presentation.home

import com.tes.android.projects.tvshowsapp.data.remote.dto.Image
import com.tes.android.projects.tvshowsapp.data.remote.dto.Rating
import com.tes.android.projects.tvshowsapp.domain.model.ShowListing

data class ShowsState(
        val shows: List<ShowListing> = emptyList(),
        val isLoading:Boolean =false,
        val isRefreshing:Boolean=false,
        val searchQuery:String="",
        val id:Int=0,
        val show: ShowListing=ShowListing(0,"","",0,image= Image(),"","","", rating = Rating()),
        val error:String=""
    )
