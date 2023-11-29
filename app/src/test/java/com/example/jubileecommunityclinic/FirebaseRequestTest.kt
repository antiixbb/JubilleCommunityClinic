package com.example.jubileecommunityclinic

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.FirebaseAuth
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.*

class FirebaseRequestTest {

    @Test
    fun testInsertAppointmentRequest() {
        val firestore = FirebaseFirestore.getInstance()
        val auth = FirebaseAuth.getInstance()
        val uid = auth.currentUser?.uid ?: "dummyUid"
        val idNumber = "1234567890"
        val firstName = "GitTest"
        val lastName = "GitTest"
        val requestedDate = "2023-12-01"
        val requestedTime = "12:00 PM"
        val dateOfRequest = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
        val requestStatus = "Pending"

        val appointmentRequest = hashMapOf(
            "uid" to uid,
            "idNumber" to idNumber,
            "firstName" to firstName,
            "lastName" to lastName,
            "requestedDate" to requestedDate,
            "requestedTime" to requestedTime,
            "dateOfRequest" to dateOfRequest,
            "requestStatus" to requestStatus
        )

        // Insert the document into the "requestedAppointments" collection
        firestore.collection("requestedAppointments")
            .add(appointmentRequest)
            .addOnSuccessListener { documentReference ->
                println("DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                println("Error adding document: $e")
            }
    }
}
