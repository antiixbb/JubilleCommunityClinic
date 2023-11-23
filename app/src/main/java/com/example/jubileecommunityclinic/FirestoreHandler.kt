// FirestoreHandler.kt
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.example.jubileecommunityclinic.models.AppointmentRequest

class FirestoreHandler {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun addAppointmentRequest(appointmentRequest: AppointmentRequest) {
        db.collection("acceptedAppointments")
            .add(appointmentRequest)
            .addOnSuccessListener { documentReference ->
                Log.d("FireStore","Success")
            }
            .addOnFailureListener { e ->
                // Handle any errors that occurred during the request
            }
    }

    // Add other Firestore methods as needed

}