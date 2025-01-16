package com.example.myapplication.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.data.entities.Food
import com.example.myapplication.databinding.ActivityDescriptionBinding
import com.example.myapplication.model.DescriptionFood

class DescriptionsAdapter(
    private val desc: List<DescriptionFood>
) :
    RecyclerView.Adapter<DescriptionsAdapter.DescriptionViewHolder>() {

    class DescriptionViewHolder(private val binding: ActivityDescriptionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(descs: DescriptionFood) {
            binding.txtFood.text = descs.name
            binding.txtDescription.text = descs.desc
            binding.txtIngredient.text = descs.ingre
            Glide.with(binding.root.context)
                .load(descs.imagePath)
                .into(binding.imageFood)
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DescriptionViewHolder {
        return DescriptionViewHolder(
            ActivityDescriptionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DescriptionViewHolder, position: Int) {
        val descs = desc[position]
        holder.bind(descs)



    }

    override fun getItemCount(): Int = desc.size
}