package com.example.myapplication.data.repository

import androidx.lifecycle.LiveData
import com.example.myapplication.data.dao.CartDao
import com.example.myapplication.data.entities.Cart

class CartRepository(private val cartDao: CartDao) {

    suspend fun addToCart(cart: Cart) = cartDao.addToCart(cart)

    suspend fun getCartItems():LiveData<List<Cart>> = cartDao.getCartItems()

}