package com.example.roomcompose.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

/**
 * @author : Mingaleev D
 * @data : 20.10.2023
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: UserViewModel
) {

   Scaffold(
       topBar = {
          CenterAlignedTopAppBar(
              title = {
                 Text(text = "Home", color = Color.Black, fontWeight = FontWeight.Bold)
              },
              colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                  containerColor = MaterialTheme.colorScheme.primary
              )
          )
       },
       floatingActionButton = {
          FloatingActionButton(
              onClick = {
                 navController.navigate("add")
              },
              containerColor = MaterialTheme.colorScheme.primary,
              contentColor = Color.Black
          )
          {
             Icon(imageVector = Icons.Default.Add, contentDescription = null)

          }
       }
   ) {

      ContentHomeView(it, navController, viewModel)

   }

}

@Composable fun ContentHomeView(
    it: PaddingValues,
    navController: NavHostController,
    viewModel: UserViewModel
) {

   val state = viewModel.state

   Column(
       modifier = Modifier.padding(it)
   ) {

      LazyColumn() {
         items(state.userList) { user ->

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {

               Column(
                   modifier = Modifier.padding(12.dp)
               ) {
                  Text(text = user.user)
                  Text(text = user.email)

                  IconButton(onClick = {
                     navController.navigate("edit/${user.id}/${user.user}/${user.email}")
                  }) {
                     Icon(imageVector = Icons.Default.Edit, contentDescription = null)
                  }

                  IconButton(onClick = { viewModel.deleteUser(user) }) {
                     Icon(imageVector = Icons.Default.Delete, contentDescription = null)
                  }
               }

            }
         }
      }
   }
}

