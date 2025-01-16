package com.example.myapplication.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.data.entities.Cart
import com.example.myapplication.databinding.ItemCartBinding
import com.example.myapplication.model.CartItem

class CartAdapter(
    private var cartItems: List<CartItem>,
    private val onQuantityChanged: (Int, Int) -> Unit,
    private val deleteCart: (cart: Cart) -> Unit
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var isItemPressed: Boolean = false

        @SuppressLint("ClickableViewAccessibility")
        fun bind(cartItem: CartItem) {

            with(binding) {
                mainContent.translationX = 0f
                txtPrice.text = cartItem.price
                txtFood.text = cartItem.name
                txtNumber.text = "${cartItem.quality}"
                Glide.with(root.context)
                    .load(cartItem.imagePath)
                    .into(imgFood)
                updateSum(cartItem.price.toDouble(), cartItem.quality)

                btnAdd.setOnClickListener {
                    onQuantityChanged(
                        cartItem.id,
                        ++cartItem.quality
                    ) // Gọi callback để cập nhật DB
                }

                btnSubtract.setOnClickListener {
                    if (cartItem.quality > 0) {
                        onQuantityChanged(
                            cartItem.id,
                            --cartItem.quality
                        ) // Gọi callback để cập nhật DB
                    }
                }
                mainContent.setOnClickListener {

                }
                // Thêm trạng thái ấn giữ cho `mainContent`
                mainContent.setOnTouchListener { _, event ->
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            // Khi người dùng bắt đầu ấn giữ
                            isItemPressed = true
                        }

                        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                            // Khi người dùng thả tay
                            isItemPressed = false
                        }
                    }
                    false // Trả về false để các sự kiện khác (như click) vẫn hoạt động
                }

                btnDelete.setOnClickListener {
                    deleteCart(Cart(cartItem.id, cartItem.foodId, cartItem.quality))
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
        holder.bind(cartItems[position])
    }

    override fun getItemCount(): Int = cartItems.size

    fun updateData(newItems: List<CartItem>) {
        this.cartItems = newItems
        notifyDataSetChanged()
    }
}
