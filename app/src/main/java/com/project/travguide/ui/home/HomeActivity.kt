package com.project.travguide.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.travguide.data.repository.AuthRepository
import com.project.travguide.data.repository.NetworkRepository
import com.project.travguide.databinding.ActivityHomeBinding
import com.project.travguide.network.RetrofitInstance
import com.project.travguide.ui.adapter.TouristSpotAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var adapter: TouristSpotAdapter

    private val viewModel: HomeViewModel by viewModels {
        val travelId = intent.getStringExtra("travelId") ?: ""
        val email = intent.getStringExtra("email") ?: ""
        val tNumber = intent.getStringExtra("t_number") ?: ""
        HomeViewModelFactory(
            AuthRepository(),
            NetworkRepository(RetrofitInstance.geoApi, RetrofitInstance.openTripApi),
            travelId,
            email
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeViewModel()
    }

    private fun setupRecyclerView() {
        adapter = TouristSpotAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.touristSpots.observe(this) { spots ->
            adapter.updateData(spots)
        }
    }
}
