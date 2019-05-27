package com.oskarijt.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [(BirdModel::class)], version = 1)
abstract class DatabaseInit: RoomDatabase()
{

    abstract fun birdDAO() : DaoInterfaces
}