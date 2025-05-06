package com.project.travguide.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.project.travguide.data.repository.AuthRepository
import com.project.travguide.databinding.ActivityLoginBinding
import com.project.travguide.ui.home.HomeActivity
<<<<<<< HEAD
=======
import com.project.travguide.utils.UiState
>>>>>>> 152816158d6e9ab8254eb5398993969fbc7d9a38

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
<<<<<<< HEAD
    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(AuthRepository())
    }
=======
    private val viewModel: LoginViewModel by viewModels()
>>>>>>> 152816158d6e9ab8254eb5398993969fbc7d9a38

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

<<<<<<< HEAD
        binding.loginButton.setOnClickListener {
            val travelId = binding.editTextTravelId.text.toString().trim()
            val email = binding.emailField.text.toString().trim()

            if (travelId.isNotEmpty() && email.isNotEmpty()) {
                loginViewModel.login(travelId, email)
            } else {
                Toast.makeText(this, "Please enter both Travel ID and Email", Toast.LENGTH_SHORT).show()
            }
        }

        loginViewModel.loginResult.observe(this) { result ->
            result.onSuccess { userDetails ->
                val intent = Intent(this, HomeActivity::class.java).apply {
                    putExtra("destination", userDetails.destination)
                    putExtra("t_number", userDetails.tNumber)
                    putExtra("travelID", userDetails.travelID)
                    putExtra("email", userDetails.email)
                }
                startActivity(intent)
                finish()
            }

            result.onFailure {
                Toast.makeText(this, it.message ?: "Login failed", Toast.LENGTH_SHORT).show()
=======
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
>>>>>>> 152816158d6e9ab8254eb5398993969fbc7d9a38
            }
        }
    }
}
