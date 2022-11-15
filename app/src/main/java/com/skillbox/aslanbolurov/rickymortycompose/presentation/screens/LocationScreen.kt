package com.skillbox.aslanbolurov.rickymortycompose.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.locations.Locations
import com.skillbox.aslanbolurov.rickymortycompose.presentation.MainViewModel
import com.skillbox.aslanbolurov.rickymortycompose.ui.theme.Gray900
import com.skillbox.aslanbolurov.rickymortycompose.ui.theme.GrayInfoText
import com.skillbox.aslanbolurov.rickymortycompose.ui.theme.InfoText
import com.skillbox.aslanbolurov.rickymortycompose.ui.theme.Title

@Composable
fun LocationScreen(
    viewModel: MainViewModel,
) {
    val lazyLocationsItems = viewModel.locations.collectAsLazyPagingItems()

    LazyColumn{
        items(lazyLocationsItems) {
            LocationItem(location = it!!)
        }

        lazyLocationsItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillParentMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                loadState.refresh is LoadState.Error -> {
                    val e = lazyLocationsItems.loadState.refresh as LoadState.Error
                    item {
                        Column(modifier = Modifier.fillParentMaxSize()) {
                            e.error.localizedMessage?.let { androidx.compose.material3.Text(text = it) }
                            Button(onClick = { retry() }) {
                                androidx.compose.material3.Text(text = "Retry2")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LocationItem(location: Locations) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Gray900),
        modifier = Modifier
            .padding(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {

            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                Title(location.name)
                InfoText("Type: ${location.type}")
                InfoText("Dimension: ${location.dimension}")
                GrayInfoText("Created: ${location.created}")
            }
        }
    }
}