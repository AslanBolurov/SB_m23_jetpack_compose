package com.skillbox.aslanbolurov.rickymortycompose.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.skillbox.aslanbolurov.rickymortycompose.data.rickAndMortyModel.characters.Result
import com.skillbox.aslanbolurov.rickymortycompose.R
import com.skillbox.aslanbolurov.rickymortycompose.presentation.MainViewModel
import com.skillbox.aslanbolurov.rickymortycompose.ui.theme.Gray900
import com.skillbox.aslanbolurov.rickymortycompose.ui.theme.GrayInfoText
import com.skillbox.aslanbolurov.rickymortycompose.ui.theme.InfoText
import com.skillbox.aslanbolurov.rickymortycompose.ui.theme.Title
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun CharacterListScreen(
    navController: NavController,
    viewModel: MainViewModel
) {
    val lazyCharacterItems = viewModel.characters.collectAsLazyPagingItems()

    LazyColumn{
        items(lazyCharacterItems) {
            CharacterItem(character = it!!, navController, viewModel)
        }
        lazyCharacterItems.apply {
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
                    val e = lazyCharacterItems.loadState.refresh as LoadState.Error
                    item {
                        Column(modifier = Modifier.fillParentMaxSize()) {
                            e.error.localizedMessage?.let { Text(text = it) }
                            Button(onClick = { retry() }) {
                                Text(text = "Retry")
                            }
                        }

                    }
                }
                loadState.append is LoadState.Error -> {
                    val e = lazyCharacterItems.loadState.append as LoadState.Error
                    item {
                        Column(
                            modifier = Modifier.fillParentMaxSize(),
                            verticalArrangement = Arrangement.Center
                        ) {
                            e.error.localizedMessage?.let { Text(text = it) }
                            Button(onClick = { retry() }) {
                                Text(text = "Retry")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CharacterItem(character: Result, navController: NavController, viewModel: MainViewModel) {
    Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Gray900),
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                navController.navigate(AllScreen.DetailAllScreen.route)
                viewModel.result = character
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            GlideImage(
                modifier = Modifier.size(150.dp),
                imageModel = {character.image},
                previewPlaceholder = R.drawable.ic_launcher_foreground
            )
            Column(
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxSize()
            ) {
                Title(character.name)
                InfoText("${character.status} - ${character.species}")
                GrayInfoText("Last location:")
                InfoText(character.location.name)
            }
        }
    }
}