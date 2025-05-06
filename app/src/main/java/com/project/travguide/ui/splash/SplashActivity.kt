package com.project.travguide.ui.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.FirebaseApp
import com.project.travguide.R
import com.project.travguide.databinding.ActivitySplashBinding
import com.project.travguide.ui.welcome.WelcomeActivity

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this)
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .asGif()
            .load(R.raw.splash_animation)
<<<<<<< HEAD
            .into(binding.gifImageView)
=======
            .into(gifImageView)
>>>>>>> 152816158d6e9ab8254eb5398993969fbc7d9a38

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, WelcomeActivity::class.java))
            finish()
        }, 1000)
    }
}
