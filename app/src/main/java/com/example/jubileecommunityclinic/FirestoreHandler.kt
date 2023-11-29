// FirestoreHandler.kt
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.example.jubileecommunityclinic.models.AppointmentRequest
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.QuerySnapshot

class FirestoreHandler {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    // created funtion to work with firebase
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

    // created funtion to work with firebase
    fun getPreviousAppointmentsForUser(
        userUid: String,
        onCompleteListener: OnCompleteListener<QuerySnapshot>
    ) {
        db.collection("previousAppointments")
            .whereEqualTo("uid", userUid)
            .get()
            .addOnCompleteListener(onCompleteListener)

    }

}