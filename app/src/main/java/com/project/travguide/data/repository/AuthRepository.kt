package com.project.travguide.data.repository

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
            }
    }
}
