package com.example.myapplication.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.activity.Description_Activity
import com.example.myapplication.databinding.PopulerItemBinding
import com.example.myapplication.model.PopularFood

class PopularAdapter(
    private val food: List<PopularFood>
) :
    RecyclerView.Adapter<PopularAdapter.PopularViewHolder>() {
    class PopularViewHolder(private val binding: PopulerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(foods: PopularFood) {
            binding.txtFood.text = foods.name
            binding.txtPrice.text = foods.price
            Glide.with(binding.root.context)
                .load(foods.imagePath)
                .into(binding.imgFood)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(
            PopulerItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {
        val foods = food[position]
        holder.bind(foods)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, Description_Activity::class.java).apply {
                putExtra("desc_id", foods.id)
            }
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int = food.size
}