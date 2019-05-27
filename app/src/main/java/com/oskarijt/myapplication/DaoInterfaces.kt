package com.oskarijt.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoInterfaces {

    @Insert
    fun insert(bird: BirdModel)

    @Query("SELECT * FROM BirdModel")
    fun getAll(): List<BirdModel>

}