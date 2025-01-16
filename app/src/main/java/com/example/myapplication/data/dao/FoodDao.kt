package com.example.myapplication.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.data.entities.Food
import com.example.myapplication.model.PopularFood

@Dao
interface FoodDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFood(food:Food)

    @Query("Select * From food where description is not null")
    suspend fun getDescriptionFoods():List<Food>

    @Query("SELECT * FROM food")
    suspend fun getAllFoods(): List<Food>

    @Update
    suspend fun updateFood(food: Food)

    @Delete
    suspend fun deleteFood(food: Food)

    @Query("SELECT * FROM food WHERE id = :id")
    suspend fun getFoodById(id: Int): Food

//    @Query("DELETE From food where id =Id")
//    suspend fun deleteFoodId(foodID:Int)

    @Query("Select name,imagePath,price,id from food where isPopular = 1")
    suspend fun getPopularFoods():List<PopularFood>

    @Query("Select * from food")
    suspend fun getAll():List<Food>



}