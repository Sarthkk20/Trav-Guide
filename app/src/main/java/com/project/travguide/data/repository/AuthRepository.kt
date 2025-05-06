package com.project.travguide.data.repository

<<<<<<< HEAD
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
=======
import com.google.firebase.firestore.FirebaseFirestore
import com.project.travguide.utils.UiState

class AuthRepository {
    private val db = FirebaseFirestore.getInstance()

    fun loginWithTravelIdAndEmail(
        travelId: String,
        email: String,
        callback: (UiState<Pair<String, String>>) -> Unit // name & destination
    ) {
        db.collection("travellers")
            .get()
            .addOnSuccessListener { snapshot ->
                val matchedDoc = snapshot.documents.find { doc ->
                    doc.getString("travelID") == travelId && doc.getString("email") == email
                }

                if (matchedDoc != null) {
//                    val name = matchedDoc.getString("name") ?: "Traveler"
                    val destination = matchedDoc.getString("destination") ?: "Unknown"
                    callback(UiState.Success(travelId to destination))
                } else {
                    callback(UiState.Error("Invalid Travel ID or Email."))
                }
            }
            .addOnFailureListener {
                callback(UiState.Error("Login failed: ${it.localizedMessage}"))
>>>>>>> 152816158d6e9ab8254eb5398993969fbc7d9a38
            }
    }
}
