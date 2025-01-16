package com.example.myapplication.Fragment


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

class CardFragment : Fragment() {
    private lateinit var binding: FragmentCardBinding
    private lateinit var adapter1: CartAdapter


    // Khởi tạo Room Database, Repository, và ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val db = AppDatabase.getDatabase(requireContext())
        val cartDao = db.cartDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val db = AppDatabase.getDatabase(requireContext())
        val cartDao = db.cartDao()

        GlobalScope.launch {
            val cartItems: List<CartItem> = cartDao.getCartItem()
            setupRecyclerView(cartItems)
        }
    }

    private fun setupRecyclerView(cartItem: List<CartItem>) {
        adapter1 = CartAdapter(cartItem)
        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(requireContext(), 1)
            adapter = adapter1
        }
    }




}

