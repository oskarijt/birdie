package com.oskarijt.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [(BirdModel::class)],
    version = 1,
    exportSchema = false
)
abstract class BirdDatabase: RoomDatabase() {

    abstract fun newBirdDao() : DaoInterfaces

    companion object {
        private var birdDbInstance: BirdDatabase? = null

        @Synchronized
        fun get(context: Context): BirdDatabase {
            if(birdDbInstance == null) {
                birdDbInstance = Room.databaseBuilder(
                    context.applicationContext,
                    BirdDatabase::class.java,
                    "bird.db")
                    .build()
            }
            return birdDbInstance!!
        }
    }
}