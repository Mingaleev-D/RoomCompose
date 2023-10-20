package com.example.roomcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author : Mingaleev D
 * @data : 20.10.2023
 */

@Database(
    entities = [Users::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase :RoomDatabase(){
   abstract fun userDao(): UserDao
}