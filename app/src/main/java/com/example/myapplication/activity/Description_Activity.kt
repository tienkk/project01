package com.example.myapplication.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.myapplication.Adapter.DescriptionsAdapter
import com.example.myapplication.R
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.entities.Food
import com.example.myapplication.data.repository.FoodRepository
import com.example.myapplication.databinding.ActivityDescriptionBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class Description_Activity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBinding
    private var descId:Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        descId = intent.getIntExtra("desc_id", -1)

        val foodDao = AppDatabase.getDatabase(applicationContext).foodDao()
        GlobalScope.launch {
            val food : Food = FoodRepository(foodDao).getFoodById(descId)
            Log.d("test3", "onCreate: ${food}")
            withContext(Dispatchers.Main){
                binding.apply {
                    txtFood.setText(food.name)
                    txtDescription.setText(food.description)
                    Glide.with(binding.root)
                        .load(food.imagePath)
                        .into(binding.imageFood)
                    txtIngredient.setText(food.ingredients)
                }
            }
        }
        binding.btnExit.setOnClickListener{
            finish()
        }



    }
}