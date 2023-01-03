package com.tes.android.projects.tvshowsapp.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.tes.android.projects.tvshowsapp.core.navigation.BottomNav
import com.tes.android.projects.tvshowsapp.core.navigation.NavGraph
import com.tes.android.projects.tvshowsapp.presentation.favorite.FavoriteViewModel
import com.tes.android.projects.tvshowsapp.presentation.search_show_listings.SearchShowListingsViewModel
//import com.tes.android.projects.tvshowsapp.presentation.NavGraphs
import com.tes.android.projects.tvshowsapp.ui.theme.TVShowsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TVShowsAppTheme {
                TvShows()
            }
        }
    }
}

@Composable
fun TvShows() {
    val navController = rememberNavController()
    val   favoriteViewModel: FavoriteViewModel = hiltViewModel()
    val   showListingViewModel: SearchShowListingsViewModel = hiltViewModel()

    Scaffold(
        bottomBar = { BottomNav(navController = navController) },
        content = { padding -> Column(modifier = Modifier.padding(padding)){
            NavGraph(navController = navController,
                favoriteViewModel=favoriteViewModel,
                showListingViewModel=showListingViewModel
            )
        } },
        backgroundColor = MaterialTheme.colors.surface,
        modifier = Modifier.fillMaxWidth()
    )
}

