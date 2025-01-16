package com.example.myapplication.data.repository

import com.example.myapplication.data.dao.FoodDao
import com.example.myapplication.data.entities.Food
import com.example.myapplication.model.PopularFood

class FoodRepository(private val foodDao:FoodDao) {

    suspend fun getDescriptionFoods():List<Food> = foodDao.getDescriptionFoods()

    suspend fun getPopularFoods():List<PopularFood> = foodDao.getPopularFoods()

    suspend fun getAllFoods(): List<Food> {
        return foodDao.getAllFoods()
    }

    suspend fun insertFood(food: Food) {
        foodDao.insertFood(food)
    }


    suspend fun getFoodById(id: Int): Food {
        return foodDao.getFoodById(id)
    }
}