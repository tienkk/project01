package com.example.myapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.entities.Food
import com.example.myapplication.data.repository.FoodRepository
import com.example.myapplication.model.PopularFood
import kotlinx.coroutines.launch

class FoodViewModel(
    private val repository: FoodRepository
) : ViewModel() {

    val descriptionFoods = MutableLiveData<List<Food>>()
    val popularFoods = MutableLiveData<List<PopularFood>>()

    fun fetchDescriptionFoods(){
        viewModelScope.launch {
            descriptionFoods.postValue(repository.getDescriptionFoods())

        }
    }
    fun fetchPopularFoods(){
        viewModelScope.launch {
//            popularFoods.postValue(repository.getPopularFoods())
        }
    }
}