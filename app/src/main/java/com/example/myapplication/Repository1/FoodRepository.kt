package com.example.myapplication.Repository1


import com.example.myapplication.data.dao.FoodDao
import com.example.myapplication.data.entities.Food
import com.example.myapplication.model.PopularFood

 class FoodRepository(private val foodDao: FoodDao) {

    // Lấy tất cả các món ăn từ Room Database
    suspend fun getAllFoods(): List<Food> {
        return foodDao.getAllFoods()
    }

    // Thêm một món ăn vào Room Database
    suspend fun insertFood(food: Food) {
        foodDao.insertFood(food)
    }

    suspend fun getAllPopularFoods() : List<PopularFood>{
        return foodDao.getPopularFoods()
    }

    // Xóa một món ăn bằng ID

    // Lấy thông tin món ăn bằng ID
    suspend fun getFoodById(id: Int): Food? {
        return foodDao.getFoodById(id)
    }
}
