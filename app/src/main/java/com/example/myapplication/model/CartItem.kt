package com.example.myapplication.model

data class CartItem(
    val id: Int,
    val foodId:Int,
    val name:String,
    val price:String,
    val imagePath:String,
    val quality:Int
)