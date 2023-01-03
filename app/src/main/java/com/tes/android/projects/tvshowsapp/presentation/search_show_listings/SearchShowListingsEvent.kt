package com.tes.android.projects.tvshowsapp.presentation.search_show_listings

import com.tes.android.projects.tvshowsapp.domain.model.ShowListing


sealed class SearchShowListingsEvent {
    object Refresh : SearchShowListingsEvent()
    object LoadShows : SearchShowListingsEvent()
    data class OnSearchQueryChange(val query: String) : SearchShowListingsEvent()
    data class OnFavoriteSelected(val show:ShowListing):SearchShowListingsEvent()
    data class DeleteFavorite(val id: Int) : SearchShowListingsEvent()

}
