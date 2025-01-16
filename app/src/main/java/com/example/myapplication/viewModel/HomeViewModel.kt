package com.example.myapplication.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.entities.Food
import com.example.myapplication.data.repository.FoodRepository
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: FoodRepository) : ViewModel() {

    // LiveData để quan sát danh sách món ăn
    private val _foodList = MutableLiveData<List<Food>>()
    val foodList: LiveData<List<Food>> get() = _foodList

    // Hàm để lấy dữ liệu từ Repository
    fun fetchFoods() {
        viewModelScope.launch {
            try {
                val foods = repository.getDescriptionFoods() // Lấy dữ liệu từ Repository
                _foodList.postValue(foods)
            } catch (e: Exception) {
                // Xử lý lỗi nếu cần
                _foodList.postValue(emptyList())
            }
        }
    }
}