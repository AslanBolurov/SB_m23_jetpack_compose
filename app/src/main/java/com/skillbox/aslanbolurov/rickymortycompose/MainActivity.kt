package com.skillbox.aslanbolurov.rickymortycompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.skillbox.aslanbolurov.rickymortycompose.presentation.MainScreen
import com.skillbox.aslanbolurov.rickymortycompose.presentation.MainViewModel
import com.skillbox.aslanbolurov.rickymortycompose.ui.theme.Gray1200
import com.skillbox.aslanbolurov.rickymortycompose.ui.theme.MyApplicationTheme

private const val TAG = "aslan555"

class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                androidx.compose.material3.Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Gray1200
                ) {
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }
}