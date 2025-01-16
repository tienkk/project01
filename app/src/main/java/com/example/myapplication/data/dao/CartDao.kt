package com.example.myapplication.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.entities.Cart
import com.example.myapplication.model.CartItem

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cart: Cart)

    @Query("SELECT * FROM cart")
    fun getCartItems(): LiveData<List<Cart>>

    @Delete
    suspend fun delete(cart: Cart)

    @Query("""
        SELECT 
            cart.id AS id,
            cart.foodID AS foodId,
            food.name AS name,
            food.price AS price,
            food.imagePath AS imagePath,
            cart.quantity AS quality
        FROM cart
        INNER JOIN food ON cart.foodID = food.id
    """)
    fun getCartItemsWithDetails(): LiveData<List<CartItem>>

    @Delete
    suspend fun DeleteCart(cart: Cart)

    @Query("UPDATE cart SET quantity = :quantity WHERE id = :cartId")
    suspend fun updateQuantity(cartId: Int, quantity: Int)
}
