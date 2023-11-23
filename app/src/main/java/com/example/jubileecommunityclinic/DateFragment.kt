// DateFragment.kt
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import com.example.jubileecommunityclinic.R

class DateFragment : Fragment() {

    private var callback: OnDateConfirmedListener? = null

    interface OnDateConfirmedListener {
        fun onDateConfirmed()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_date, container, false)

        val calendarView: CalendarView = view.findViewById(R.id.calendarView)
        val confirmButton: Button = view.findViewById(R.id.confirmDateButton)

        confirmButton.setOnClickListener {
            // Notify the activity that the date is confirmed
            callback?.onDateConfirmed()
        }

        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Ensure the hosting activity implements the callback interface
        try {
            callback = context as OnDateConfirmedListener
        } catch (e: ClassCastException) {
            throw ClassCastException("$context must implement OnDateConfirmedListener")
        }
    }
}
