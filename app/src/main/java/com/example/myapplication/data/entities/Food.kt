package com.example.myapplication.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
data class Food(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val imagePath : String,
    val description:String,
    val ingredients :String,
    val price :String,
    val isPopular:Boolean= false
)