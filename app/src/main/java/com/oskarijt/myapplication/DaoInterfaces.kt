package com.oskarijt.myapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DaoInterfaces {

    @Insert
    fun insert(bird: BirdModel)

    @Query("SELECT * FROM BirdModel")
    fun getAll(): LiveData<List<BirdModel>>

}