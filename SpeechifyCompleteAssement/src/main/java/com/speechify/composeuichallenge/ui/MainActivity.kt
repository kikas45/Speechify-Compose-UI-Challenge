package com.speechify.composeuichallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.speechify.composeuichallenge.ui.navigation.AppNavGraph
import com.speechify.composeuichallenge.ui.theme.ComposeUIChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeUIChallengeTheme {

                AppNavGraph()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun GreetingPreview() {
    ComposeUIChallengeTheme {
        AppNavGraph()
    }
}
