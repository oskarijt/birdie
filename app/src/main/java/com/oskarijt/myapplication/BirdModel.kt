package com.oskarijt.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class BirdModel(

    @PrimaryKey(autoGenerate = true) val uuid: Long,

    val species: String,
    val notes: String,
    val rarity: Int,
    var location: String?,
    var caughtTimestamp: Long?) {

    fun getBirdSpotDate(): Date {
        return Date(caughtTimestamp ?: 12345)
    }
}