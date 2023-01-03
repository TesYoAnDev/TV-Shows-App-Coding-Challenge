package com.tes.android.projects.tvshowsapp.core.mapper

import com.tes.android.projects.tvshowsapp.data.local.entity.FavoriteShowListingEntity
import com.tes.android.projects.tvshowsapp.data.local.entity.ShowListingEntity
import com.tes.android.projects.tvshowsapp.data.remote.dto.ShowInfoDto
import com.tes.android.projects.tvshowsapp.domain.model.ShowListing


fun ShowListingEntity.toShowListing(): ShowListing {
    return ShowListing(
        id = id!!,
        name = name,
        type = type,
        runtime = runtime,
        image=image,
        status=status,
        summary =summary,
        premiered = premiered,
        rating = rating!!
    )
}

fun ShowListing.toShowListingEntity(): ShowListingEntity {
    return ShowListingEntity(
        id=id,
        name = name,
        type = type,
        runtime = runtime,
        image= image,
        status=status,
        summary =summary,
        premiered = premiered,
        rating = rating
    )
}

fun ShowListing.toFavoriteShowListingEntity(): FavoriteShowListingEntity {
    return FavoriteShowListingEntity(
        id=id,
        name = name,
        type = type,
        runtime = runtime,
        image= image,
        status=status,
        summary =summary,
        premiered = premiered,
        rating = rating
    )
}

fun ShowInfoDto.toShowListing(): ShowListing {
    return ShowListing(
        id = id,
        name = name,
        type = type,
        premiered= premiered,
        status = status,
        runtime = runtime ?:0,
        summary = summary,
        image = image,
        rating = rating
    )
}

fun FavoriteShowListingEntity.toShowListing(): ShowListing {
    return ShowListing(
        id = id!!,
        name = name,
        type = type,
        runtime = runtime,
        image=image,
        status=status,
        summary =summary,
        premiered = premiered,
        rating = rating!!
    )
}
