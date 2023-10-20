package com.example.roomcompose.ui.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.roomcompose.data.Users

/**
 * @author : Mingaleev D
 * @data : 20.10.2023
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    navController: NavHostController,
    viewModel: UserViewModel,
    id: Int,
    user: String?,
    email: String?
) {

   Scaffold(
       topBar = {
          CenterAlignedTopAppBar(
              title = {
                 Text(text = "Edit views", color = Color.Black, fontWeight = FontWeight.Bold)
              },
              colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                  containerColor = MaterialTheme.colorScheme.primary
              ),
              navigationIcon = {
                 IconButton(onClick = { navController.popBackStack() }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)

                 }
              }
          )
       }
   ) {
      ContentEditView(
          it, navController,
          viewModel,
          id,
          user,
          email
      )
   }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable fun ContentEditView(
    it: PaddingValues,
    navController: NavHostController,
    viewModel: UserViewModel,
    id: Int,
    user: String?,
    email: String?
) {

   var user by remember { mutableStateOf("") }
   var email by remember { mutableStateOf("") }

   Column(
       modifier = Modifier
           .padding(it)
           .padding(top = 30.dp)
           .fillMaxSize(),
       horizontalAlignment = Alignment.CenterHorizontally
   ) {

      OutlinedTextField(
          value = user ?: "",
          onValueChange = {
             user = it
          },
          label = {
             Text(text = "User")
          }, modifier = Modifier
          .fillMaxWidth()
          .padding(horizontal = 30.dp)
          .padding(bottom = 15.dp)
      )

   }
   OutlinedTextField(
       value = email ?: "",
       onValueChange = {
          email = it
       },
       label = {
          Text(text = "Email")
       }, modifier = Modifier
       .fillMaxWidth()
       .padding(horizontal = 30.dp)
       .padding(bottom = 15.dp)
   )

   Button(onClick = {
      val userOb = Users(
          id = id,
          user = user!!,
          email = email!!

      )
      viewModel.updateUser(users = userOb)
      navController.popBackStack()
   }) {
      Text(text = "Edit")
   }

}
