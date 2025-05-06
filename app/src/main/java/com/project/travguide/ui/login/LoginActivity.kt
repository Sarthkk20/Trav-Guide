package com.project.travguide.ui.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.project.travguide.data.repository.AuthRepository
import com.project.travguide.databinding.ActivityLoginBinding
import com.project.travguide.ui.home.HomeActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(AuthRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
            }
        }
    }
}
