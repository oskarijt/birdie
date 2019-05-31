package com.oskarijt.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class BirdModel(

    @PrimaryKey(autoGenerate = true) val uuid: Long,

    var species: String,
    var notes: String?,
    var rarity: String,
    var lat: String?,
    var lon: String?,
    var spotted_at: Long?) {

    fun getBirdSpotDate(): Date {
        return Date(spotted_at ?: 12345)
    }
}