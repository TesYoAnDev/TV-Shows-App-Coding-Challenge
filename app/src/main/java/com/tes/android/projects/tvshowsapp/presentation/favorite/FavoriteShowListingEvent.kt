package com.tes.android.projects.tvshowsapp.presentation.favorite

sealed class FavoriteShowListingEvent{
    data class OnDeleteSelected(val id: Int) : FavoriteShowListingEvent()
    object LoadFavoriteShows : FavoriteShowListingEvent()

}
