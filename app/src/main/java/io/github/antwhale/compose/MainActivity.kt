package io.github.antwhale.compose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import io.github.antwhale.compose.api.TweetsyAPI
import io.github.antwhale.compose.screens.CategoryScreen
import io.github.antwhale.compose.screens.DetailScreen
import io.github.antwhale.compose.ui.theme.PracticeComposeWithMVVMTheme
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    val TAG = MainActivity::class.java.simpleName

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PracticeComposeWithMVVMTheme {
//                CategoryScreen()
//                DetailScreen()
                Scaffold(
                    topBar = {
                        TopAppBar(title = {
                            Text(text = "Tweetsy")
                        }, colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black, titleContentColor = Color.White))
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "category") {
        composable(route = "category") {
            CategoryScreen(onClick = {
                navController.navigate("detail/$it")
            })
        }
        composable(route = "detail/{category}",
            arguments = listOf(
                navArgument("category") {
                    type = NavType.StringType
                }
        )
        ){
            DetailScreen()
        }
    }

//    val navController = rememberNavController()
//    NavHost(navController = navController, startDestination = "registration") {
//        composable(route = "registration") {
//            RegistrationScreen(navController)
//        }
//
//        composable(route = "login") {
//            LoginScreen()
//        }
//
//        composable(route = "main/{email}", arguments = listOf(
//            navArgument("email") {
//                type = NavType.StringType
//            }
//        )) {
//            val email = it.arguments!!.getString("email")
//            MainScreen(email!!)
//        }
//
//    }
}

@Composable
fun RegistrationScreen(navController: NavController) {
    Text(
        text = "Registration",
        style = MaterialTheme.typography.headlineLarge,
        modifier = Modifier.clickable {
            navController.navigate(route = "main/olollolo339@naver.com")
        }
    )
}

@Composable
fun LoginScreen() {
    Text(text = "Login", style = MaterialTheme.typography.headlineLarge)
}

@Composable
fun MainScreen(email: String) {
    Text(text = "MainScreen - $email", style = MaterialTheme.typography.headlineLarge)
}
