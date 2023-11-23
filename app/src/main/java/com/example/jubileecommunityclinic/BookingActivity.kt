package com.example.jubileecommunityclinic

import DateFragment
import TimeFragment
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class BookingActivity : AppCompatActivity(), DateFragment.OnDateConfirmedListener, TimeFragment.OnTimeConfirmedListener {

    companion object {
        private val activityToMenuItemMap = mapOf(
            HomeActivity::class.java to R.id.menu_home,
            BookingActivity::class.java to R.id.menu_booking,
            UpcomingActivity::class.java to R.id.menu_upcoming,
            PreviousActivity::class.java to R.id.menu_previous
        )
    }

    private lateinit var menu: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        menu = findViewById(R.id.bottomNavigation)

        // Set the selected menu item based on the current activity
        activityToMenuItemMap[this::class.java]?.let { menu.selectedItemId = it }

        // Handling the menu
        menu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    navigateTo(HomeActivity::class.java)
                    true
                }
                R.id.menu_booking -> {
                    navigateTo(BookingActivity::class.java)
                    true
                }
                R.id.menu_upcoming -> {
                    navigateTo(UpcomingActivity::class.java)
                    true
                }
                R.id.menu_previous -> {
                    navigateTo(PreviousActivity::class.java)
                    true
                }
                else -> false
            }
        }

        // Load the initial fragment
        if (savedInstanceState == null) {
            loadDateFragment()
        }
    }

    private fun navigateTo(destination: Class<out AppCompatActivity>) {
        startActivity(Intent(this@BookingActivity, destination))
    }

    private fun loadDateFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DateFragment())
            .commit()
    }

    override fun onDateConfirmed() {
        // Date confirmed, load the TimeFragment
        loadTimeFragment()
    }

    private fun loadTimeFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, TimeFragment())
            .commit()
    }

    override fun onTimeConfirmed() {
        // Time confirmed, handle the complete booking process
        // For example, send the booking request or navigate to the next screen.
    }
}
