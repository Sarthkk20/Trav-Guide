package com.project.travguide.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.project.travguide.databinding.ActivityLoginBinding
import com.project.travguide.ui.home.HomeActivity
import com.project.travguide.utils.UiState

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val travelId = binding.editTextTravelId.text.toString().trim()
            val email = binding.editTextEmail.text.toString().trim()

            if (travelId.isNotEmpty() && email.isNotEmpty()) {
                viewModel.login(travelId, email)
            } else {
                Toast.makeText(this, "Enter both Travel ID and Email", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loginState.observe(this) { state ->
            when (state) {
                is UiState.Loading -> {
                    binding.btnLogin.isEnabled = true
                }

                is UiState.Success -> {
                    val (name, destination) = state.data
                    val intent = Intent(this, HomeActivity::class.java).apply {
                        putExtra("name", name)
                        putExtra("destination", destination)
                    }
                    startActivity(intent)
                    finish()
                }

                is UiState.Error -> {
                    binding.btnLogin.isEnabled = true
                    Toast.makeText(this, state.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
