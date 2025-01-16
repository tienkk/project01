package com.example.myapplication.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemCartBinding
import com.example.myapplication.model.CartItem
class CartAdapter(
    private val cart: List<CartItem>
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(private val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: CartItem) {
            with(binding) {
                // Thiết lập dữ liệu ban đầu
                txtPrice.text = cartItem.price
                txtFood.text = cartItem.name
                txtNumber.text = "${cartItem.quality}"
                Glide.with(root.context)
                    .load(cartItem.imagePath)
                    .into(imgFood)
                updateSum(cartItem.price.toDouble(), cartItem.quality)

                // Xử lý sự kiện tăng/giảm số lượng
                var number = cartItem.quality

                btnAdd.setOnClickListener {
                    number++
                    txtNumber.text = "$number"
                    updateSum(cartItem.price.toDouble(), number)
                }

                btnSubtract.setOnClickListener {
                    if (number > 0) {
                        number--
                        txtNumber.text = "$number"
                        updateSum(cartItem.price.toDouble(), number)
                    }
                }
            }
        }

        private fun ItemCartBinding.updateSum(price: Double, quantity: Int) {
            txtSum.text = "${price * quantity}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CartViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(cart[position])
    }

    override fun getItemCount(): Int = cart.size
}
