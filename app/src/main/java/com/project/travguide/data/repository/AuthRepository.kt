package com.project.travguide.data.repository

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.project.travguide.data.model.UserDetails

class AuthRepository {

    private val db = FirebaseFirestore.getInstance()

    fun getUserDetails(travelId: String, email: String, callback: (UserDetails?) -> Unit) {
        db.collection("travelers")
            .whereEqualTo("travelID", travelId.trim())
            .whereEqualTo("email", email.trim())
            .get()
            .addOnSuccessListener { documents ->
                if (!documents.isEmpty) {
                    val document = documents.first()
                    val user = document.toObject(UserDetails::class.java)
                    callback(user)
                } else {
                    Log.e("AuthRepository", "No matching document for provided credentials.")
                    callback(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("AuthRepository", "Error fetching user details", exception)
                callback(null)
            }
    }
}
