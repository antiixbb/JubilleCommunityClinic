// TimeFragment.kt
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import com.example.jubileecommunityclinic.R
import com.example.jubileecommunityclinic.models.AppointmentRequest
import java.text.SimpleDateFormat
import java.util.Date

class TimeFragment : Fragment() {

    private lateinit var confirmTimeButton: Button
    private lateinit var timePicker: TimePicker

    private var callback: OnTimeConfirmedListener? = null

    interface OnTimeConfirmedListener {
        fun onTimeConfirmed()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_time, container, false)

        // Initialize your views
        confirmTimeButton = view.findViewById(R.id.confirmTimeButton)
        timePicker = view.findViewById(R.id.timePicker)

        // Set up the click listener for the Confirm Time button
        confirmTimeButton.setOnClickListener {
            // Handle the click event
            onConfirmTimeClicked()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Ensure the hosting activity implements the callback interface
        try {
            callback = context as OnTimeConfirmedListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement OnTimeConfirmedListener")
        }
    }

    private fun onConfirmTimeClicked() {
        // Get the selected time from the TimePicker
        val selectedHour = timePicker.hour
        val selectedMinute = timePicker.minute

        // Format the selected time
        val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)

        // Get other required data (replace with your actual data retrieval logic)
        val userId = "user_uid" // Replace with actual user UID
        val idNumber = "user_id_number" // Replace with actual user ID number
        val firstName = "user_first_name" // Replace with actual user first name
        val lastName = "user_last_name" // Replace with actual user last name
        val requestedDate = "requested_date" // Replace with actual requested date
        val dateOfRequest = SimpleDateFormat("yyyy-MM-dd").format(Date())

        // Create an AppointmentRequest object
        val appointmentRequest = AppointmentRequest(
            uid = userId,
            idNumber = idNumber,
            firstName = firstName,
            lastName = lastName,
            requestedDate = requestedDate,
            requestedTime = formattedTime,
            dateOfRequest = dateOfRequest
        )

        // Send the data to Firestore (using FirestoreHandler or your preferred method)
        FirestoreHandler().addAppointmentRequest(appointmentRequest)
    }
}
