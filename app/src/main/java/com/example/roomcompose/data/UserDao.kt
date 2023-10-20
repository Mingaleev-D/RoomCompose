package com.example.roomcompose.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * @author : Mingaleev D
 * @data : 20.10.2023
 */

@Dao
interface UserDao {

   @Query("SELECT * FROM users")
   fun getAllUsers(): Flow<List<Users>>

   @Query("SELECT * FROM users WHERE id = :id")
   fun getUSerById(id: Int): Flow<Users>

   @Insert
   suspend fun insertUser(user: Users)

   @Update
   suspend fun updateUser(user: Users)

   @Delete
   suspend fun deleteUser(user: Users)
}