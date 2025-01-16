package com.example.myapplication.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Adapter.PopularAdapter
import com.example.myapplication.data.database.AppDatabase
import com.example.myapplication.data.repository.FoodRepository
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.viewModel.HomeViewModel
import com.example.myapplication.viewModel.HomeViewModelFactory
import com.example.myapplication.model.PopularFood
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: HomeViewModel
    private lateinit var adapter1: PopularAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Khởi tạo Room Database, Repository, và ViewModel
        val foodDao = AppDatabase.getDatabase(requireContext()).foodDao()
        val repository = FoodRepository(foodDao)
        val factory = HomeViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Thiết lập Image Slider
        val db = AppDatabase.getDatabase(requireContext())
        val foodDao = db.foodDao()
        val foodRepository = FoodRepository(foodDao)
        GlobalScope.launch {
            val popularFood: List<PopularFood> = foodRepository.getPopularFoods()
            setupRecyclerView(popularFood)
        }
        // Quan sát dữ liệu từ ViewModel

        // Lấy dữ liệu từ ViewModel
        viewModel.fetchFoods()

    }

    private fun setupRecyclerView(popularFood: List<PopularFood>) {
        adapter1 = PopularAdapter(popularFood)
        binding.RVPopular.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = adapter1
        }
    }
}




