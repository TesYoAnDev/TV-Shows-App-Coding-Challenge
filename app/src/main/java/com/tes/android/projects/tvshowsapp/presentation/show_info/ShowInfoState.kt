package com.tes.android.projects.tvshowsapp.presentation.show_info

import com.tes.android.projects.tvshowsapp.domain.model.ShowListing

data class ShowInfoState(
    val show: ShowListing? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)