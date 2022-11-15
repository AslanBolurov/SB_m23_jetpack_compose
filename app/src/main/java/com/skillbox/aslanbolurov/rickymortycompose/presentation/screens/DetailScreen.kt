package com.skillbox.aslanbolurov.rickymortycompose.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skillbox.aslanbolurov.rickymortycompose.R
import com.skillbox.aslanbolurov.rickymortycompose.presentation.MainViewModel
import com.skillbox.aslanbolurov.rickymortycompose.ui.theme.Gray1200
import com.skillbox.aslanbolurov.rickymortycompose.ui.theme.Gray900
import com.skillbox.aslanbolurov.rickymortycompose.ui.theme.Grey120
import com.skillbox.aslanbolurov.rickymortycompose.ui.theme.Grey80
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun DetailScreen(viewModel: MainViewModel) {
    val character = viewModel.result
    viewModel.getEpisodeList(character?.episode)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray1200)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 15.dp, start = 30.dp, end = 30.dp)
        ) {
            item {
                GlideImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(5.dp)),
                    imageModel = {character?.image},
                    imageOptions = ImageOptions(contentScale = ContentScale.Crop),
                    previewPlaceholder = R.drawable.ic_launcher_foreground
                )
                Text(
                    text = character?.name ?: "",
                    lineHeight = 35.sp,
                    fontSize = 35.sp,
                    color = Grey80,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Divider(
                    modifier = Modifier.padding(top = 10.dp, bottom = 10.dp),
                    color = Grey80,
                    thickness = 1.dp
                )

                GrayDetailText("Live status:")
                InfoDetailText("${character?.status}")
                GrayDetailText("Species and gender:")
                InfoDetailText("${character?.species} (${character?.gender})")
                GrayDetailText("Last known location:")
                InfoDetailText(character?.location?.name ?: "")

                val listEp = mutableListOf<String>()
                viewModel.episodes.forEach{
                    listEp.add(it.name)
                }

                Text(
                    modifier = Modifier.padding(top = 30.dp),
                    text = "Episodes",
                    fontSize = 35.sp,
                    color = Grey80,
                    fontWeight = FontWeight.Medium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            itemsIndexed(viewModel.episodes) {_, item ->
                Card(
                    shape = RoundedCornerShape(10.dp),
                    colors = CardDefaults.cardColors(containerColor = Gray900),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp, top = 10.dp)
                ) {

                    Column() {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                fontSize = 20.sp,
                                text = item.name,
                                color = Grey80,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .requiredWidth(200.dp)
                            )

                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = item.episode,
                                color = Grey120
                            )
                        }

                        Row() {
                            Text(
                                modifier = Modifier.padding(10.dp),
                                text = item.air_date,
                                color = Grey120
                            )
                        }
                    }
                }

            }
        }
    }
}

@Composable
fun InfoDetailText(title: String) {
    Text(
        text = title,
        fontSize = 24.sp,
        color = Grey80
    )
}

@Composable
fun GrayDetailText(title: String) {
    Text(
        modifier = Modifier.padding(top = 30.dp),
        text = title,
        fontSize = 20.sp,
        color = Grey120
    )
}