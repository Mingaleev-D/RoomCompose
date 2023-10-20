package com.example.roomcompose.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import androidx.room.Room
import com.example.roomcompose.data.UserDatabase
import com.example.roomcompose.ui.theme.RoomComposeTheme
import com.example.roomcompose.ui.views.AddScreen
import com.example.roomcompose.ui.views.EditScreen
import com.example.roomcompose.ui.views.HomeScreen
import com.example.roomcompose.ui.views.UserViewModel

class MainActivity : ComponentActivity() {

   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         RoomComposeTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {

               val database = Room.databaseBuilder(this, UserDatabase::class.java, "user_db")
                   .allowMainThreadQueries()
                   .build()
               val dao = database.userDao()
               val viewModel = UserViewModel(dao)
               NavManager(viewModel)

            }
         }
      }
   }
}

@Composable
fun NavManager(
    viewModel: UserViewModel
) {

   val navController = rememberNavController()

   NavHost(navController = navController, startDestination = "home") {
      composable("home") {
         HomeScreen(
             navController = navController,
             viewModel = viewModel
         )
      }

      composable("add") {
         AddScreen(
             navController,
             viewModel
         )
      }

      composable(
          "edit/{id}/{name}/{email}",
          arguments = listOf(
              navArgument("id") {
                 type = NavType.IntType
              },
              navArgument("name") {
                 type = NavType.StringType
              },
              navArgument("email") {
                 type = NavType.StringType
              }
          )
      ) {
         EditScreen(
             navController,
             viewModel,
             it!!.arguments!!.getInt("id"),
             it?.arguments?.getString("name"),
             it?.arguments?.getString("email")
         )
      }
   }

}



