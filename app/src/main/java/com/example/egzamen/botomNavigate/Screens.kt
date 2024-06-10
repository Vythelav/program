package com.example.egzamen.botomNavigate

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.egzamen.AppBottomNavigation


@Composable
fun Screen1(navController: NavController) {
    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        text = "Screen 1",
        textAlign = TextAlign.Center
    )
    AppBottomNavigation(navController = navController)
}
@Composable
fun Screen2() {
    val navController = rememberNavController()

    Text(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentHeight(),
        text = "Screen 2",
        textAlign = TextAlign.Center
    )
    AppBottomNavigation(navController = navController)
}

