package com.tes.android.projects.tvshowsapp.data.remote.datasource

import com.tes.android.projects.tvshowsapp.data.remote.dto.ShowListingDto

interface RemoteDataSource {

    suspend fun getShowListingFromApi(): ShowListingDto
}