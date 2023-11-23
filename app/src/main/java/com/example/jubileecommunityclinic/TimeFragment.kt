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

class TimeFragment : Fragment() {

    private var callback: OnTimeConfirmedListener? = null

    interface OnTimeConfirmedListener {
        fun onTimeConfirmed()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_time, container, false)

        val timePicker: TimePicker = view.findViewById(R.id.timePicker)
        val confirmButton: Button = view.findViewById(R.id.confirmTimeButton)

        confirmButton.setOnClickListener {
            // Notify the activity that the time is confirmed
            callback?.onTimeConfirmed()
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
}
