package com.example.myapplication.Fragment


import android.graphics.Canvas
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Adapter.CartAdapter
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.databinding.FragmentCardBinding
import com.example.myapplication.model.CartItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class CardFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding
    private lateinit var adapter1: CartAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cartDao = AppDatabase.getDatabase(requireContext()).cartDao()

        adapter1 = CartAdapter(emptyList(), onQuantityChanged = { cartId, quantity ->
            lifecycleScope.launch {
                cartDao.updateQuantity(cartId, quantity) // Cập nhật số lượng trong DB
            }
        },
            deleteCart = { cart -> lifecycleScope.launch { cartDao.delete(cart) } }
        )

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = adapter1
        }

        // Quan sát dữ liệu từ ViewModel
        cartDao.getCartItemsWithDetails().observe(viewLifecycleOwner, Observer { cartItems ->
            adapter1.updateData(cartItems)
        })
        val itemTouchHelper = createItemTouchHelper(adapter1)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

    fun createItemTouchHelper(adapter: CartAdapter): ItemTouchHelper {
        return ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                val holder = viewHolder as CartAdapter.CartViewHolder
                return if (holder.isItemPressed) {
                    makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
                } else {
                    0 // Không cho phép vuốt
                }
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false // Không hỗ trợ kéo thả
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                val holder = viewHolder as CartAdapter.CartViewHolder
                val binding = holder.binding
                val mainContent = binding.mainContent
                val deleteButton = binding.btnDelete

                val maxSwipeDistance = deleteButton.width.toFloat()
                val translationX = binding.mainContent.translationX
                val threshold = maxSwipeDistance * 0.7f
                if (!isCurrentlyActive) {
                    // Tự động hoàn thành vuốt sang trái
                    if (-translationX > threshold) {
                        mainContent.translationX = -maxSwipeDistance
                    }
                    // Tự động hoàn thành vuốt sang phải
                    else if (-translationX < threshold) {
                        mainContent.translationX = 0f
                    }
                } else {
                    mainContent.translationX = (translationX + dX).coerceIn(-maxSwipeDistance, 0f)
                }
                if (isCurrentlyActive) {
                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        0f,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }
            }

            override fun getSwipeThreshold(viewHolder: RecyclerView.ViewHolder): Float {
                return 0.12f // Vuốt qua 50% chiều rộng của nút xóa sẽ coi là hoàn tất
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }

        })
    }
}
