package com.example.egzamen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.egzamen.botomNavigate.Screen1
import com.example.egzamen.botomNavigate.Screen2

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            MainScreen(navController = navController)
        }
    }
}

@Composable
fun NavGraph(navHostController: NavController) {
    NavHost(navController = navHostController as NavHostController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController = navHostController)
        }
        composable("screen_1") {
            Screen1()
        }
        composable("screen_2") {
            Screen2()
        }
 }
}

@Composable
fun LoginScreen(navController: NavController) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Вход",
            fontSize = 50.sp,
            modifier = Modifier.padding(20.dp)
        )
        TextField(
            value = login,
            onValueChange = { login = it },
            modifier = Modifier.padding(vertical = 16.dp),
            placeholder = { Text("Логин", fontSize = 20.sp) }
        )
        TextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.padding(vertical = 16.dp),
            placeholder = { Text("Пароль", fontSize = 20.sp) },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        if (showError) {
            Text(
                text = "Неправильный логин или пароль",
                color = Color.Red,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
        Button(
            onClick = {
                if (login == "user" && password == "password") {

                } else {
                    showError = true
                }
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Вход", fontSize = 30.sp)
        }
    }
}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val isLoggedIn = remember { mutableStateOf(false) }

    if (isLoggedIn.value) {
        Scaffold(
            bottomBar = {
                AppBottomNavigation(navController = navController)
            }
        ) {
            NavGraph(navHostController = navController)
        }
    } else {
        LoginScreen(navController = navController) { isLoggedIn.value = true }
    }
}

@Composable
fun AppBottomNavigation(navController: NavController ) {
    val listItems = listOf(
        BottomItem.Screen1,
        BottomItem.Screen2,
    )
    NavigationBar(
        Modifier.background(Color.White)
    ) {
        val backStackEntry by navController.currentBackStackEntryAsState()
        val currenrRout = backStackEntry?.destination?.route
        listItems.forEach { item ->
            NavigationBarItem(
                selected = currenrRout == item.route,
                onClick = {
                    navController.navigate(item.route)
                },
                icon = {
                    Icon(painter = painterResource(id = item.iconId), contentDescription = "Icon")
                },
                label = {
                    Text(text = item.title, fontSize = 15.sp)
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Red,
                    unselectedIconColor = Color.Gray
                )
            )
        }
    }
}

sealed class BottomItem (val title:String, val iconId: Int, val route: String){
    object Screen1: BottomItem("Расписание", R.drawable.ras,"screen_1")
    object Screen2: BottomItem("Редактирование", R.drawable.red,"screen_2")
}