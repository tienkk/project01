package com.example.myapplication.data.entities.relationships

import androidx.room.Embedded
import androidx.room.Relation
import com.example.myapplication.data.entities.Cart
import com.example.myapplication.data.entities.Food

data class CartWithFood (
    @Embedded val cart: Cart,
    @Relation(
        parentColumn = "fooID",
        entityColumn = "id"
    )
    val food: Food

)
