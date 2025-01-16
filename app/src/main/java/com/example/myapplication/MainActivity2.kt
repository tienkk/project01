package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.myapplication.Until.saveDrawableToInternalStorage
import com.example.myapplication.data.dao.FoodDao
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.entities.Cart
import com.example.myapplication.data.entities.Food
import com.example.myapplication.databinding.ActivityMain2Binding
import com.example.myapplication.model.PopularFood
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var navController = findNavController(R.id.fragmentContainerView2)
        binding.bottomNavigationView.setupWithNavController(navController)
        val sampleFoods = listOf(
            Food(
                name = "Pizza",
                imagePath = saveDrawableToInternalStorage(applicationContext, R.drawable.food1, "pizza.jpg"),
                description = "Delicious cheese pizza topped with fresh tomato sauce and mozzarella.",
                ingredients = "Cheese, Tomato Sauce, Dough, Basil",
                price = "9.99",
                isPopular = true
            ),
            Food(
                name = "Burger",
                imagePath = saveDrawableToInternalStorage(applicationContext, R.drawable.food2, "burger.jpg"),
                description = "A juicy beef burger with fresh lettuce and tomato.",
                ingredients = "Beef Patty, Lettuce, Tomato, Bun",
                price = "5.99",
                isPopular = true
            ),
            Food(
                name = "Pasta",
                imagePath = saveDrawableToInternalStorage(applicationContext, R.drawable.food3, "pasta.jpg"),
                description = "Creamy Alfredo pasta with Parmesan cheese.",
                ingredients = "Pasta, Cream, Cheese, Garlic",
                price = "7.99",
                isPopular = true
            )
        )
//        insert(sampleFoods)

    }

    fun insert(foodDao:List<Food>) {

        GlobalScope.launch {
            val context = applicationContext
            val db = AppDatabase.getDatabase(context)
            val foodDaos = db.foodDao()
            val carts = db.cartDao()
            val cart1 = carts.getCartItems()
//            val listCart = carts.getCartItem()

            val listFoods = foodDaos.getDescriptionFoods()
            foodDao.forEach {
                foodDaos.insertFood(it)
//            val listFoods = foodDao.getAll()
//            listFoods.forEach {
//                foodDao.updateFood(Food(it.id,it.name,it.imagePath,it.description,it.ingredients,it.price.replace("$","")))
            }
        }
    }
}
