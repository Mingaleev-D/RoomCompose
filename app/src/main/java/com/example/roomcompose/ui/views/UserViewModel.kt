package com.example.roomcompose.ui.views

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomcompose.data.UserDao
import com.example.roomcompose.data.Users
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

/**
 * @author : Mingaleev D
 * @data : 20.10.2023
 */

class UserViewModel(
    private val dao: UserDao
) : ViewModel() {

   var state by mutableStateOf(UserUIState())
      private set

   init {
      viewModelScope.launch {
         dao.getAllUsers().collectLatest {
            state = state.copy(userList = it)
         }
      }
   }

   fun getUser(users: Users) = viewModelScope.launch {
      dao.insertUser(users)
   }

   fun updateUser(users: Users) = viewModelScope.launch {
      dao.updateUser(users)
   }

   fun deleteUser(users: Users) = viewModelScope.launch {
      dao.deleteUser(users)
   }
}

data class UserUIState(
    val userList: List<Users> = emptyList()
)