package com.tes.android.projects.tvshowsapp.presentation.show_detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.tes.android.projects.tvshowsapp.R
import com.tes.android.projects.tvshowsapp.core.components.RatingBar
import com.tes.android.projects.tvshowsapp.ui.theme.DarkBlue

@Composable
fun ShowDetailScreen(
    viewModel: ShowDetailViewModel = hiltViewModel(),
    navController: NavController
) {
    val state = viewModel.state
    if (state.error == null) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DarkBlue)
                .padding(16.dp)
        ) {
            state.show?.let { show ->
                val imagePainter = rememberAsyncImagePainter(show.image.medium)
                val showName = show.name
                TopAppBarContent(
                    navController,
                    showName
                )
                Spacer(modifier = Modifier.height(8.dp))
                Image(
                    painter = imagePainter,
                    contentDescription = "",
                    Modifier.size(300.dp)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Name: ${show.name}",
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Type: ${show.type}",
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Premiered:  ${show.premiered.toString()}",
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Status: ${show.status}",
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Run time : ${show.runtime}",
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.padding()) {
                    RatingBar(rating = show.rating.average)
                    Text(
                        text = "${show.rating.average}",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Summary of ${show.name} : ${show.summary.toString()}",
                    fontSize = 14.sp,
                    modifier = Modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(8.dp))
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        if (state.isLoading) {
            CircularProgressIndicator()
        } else if (state.error != null) {

            val imagePainter = rememberAsyncImagePainter(R.drawable.no_data_found)

            Box(
                modifier = Modifier
                    .padding(start = 2.dp)
                    .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    painter = imagePainter, contentDescription = "No data",
                )
            }
        }
    }
}

@Composable
fun TopAppBarContent( navController: NavController, name: String) {
    TopAppBar(
        title = {
            Text(
                text = name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center,
            )
        },
        backgroundColor = MaterialTheme.colors.background,
        elevation = 4.dp,
        navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = "Go back",
                )
            }
        }
    )
}
