package com.project.travguide

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp

class FirebaseAuth : Application() {
    override fun onCreate() {
        super.onCreate()
        var app = FirebaseApp.initializeApp(this)
        Log.d("FirebaseAuth", "Firebase initialized: ${app != null}")
    }
}
