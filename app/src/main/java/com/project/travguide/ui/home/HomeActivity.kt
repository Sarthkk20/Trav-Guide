package com.project.travguide.ui.home

<<<<<<< HEAD
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
=======
import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val textView = TextView(this).apply {
            text = "Welcome to TravGuide!"
            textSize = 24f
            gravity = Gravity.CENTER
            setPadding(32, 32, 32, 32)
        }

        setContentView(textView)
    }
}
>>>>>>> 152816158d6e9ab8254eb5398993969fbc7d9a38
