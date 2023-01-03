package com.tes.android.projects.tvshowsapp.presentation.home

import com.tes.android.projects.tvshowsapp.domain.model.ShowListing

sealed class ShowsEvent {
        object LoadShows : ShowsEvent()
        data class OnFavoriteSelected(val show: ShowListing):ShowsEvent()
        data class DeleteFavorite(val id: Int) : ShowsEvent()
}