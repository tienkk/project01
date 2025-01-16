package com.example.myapplication.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cart")
data class Cart(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val foodID: Int,
    val quantity : Int
)