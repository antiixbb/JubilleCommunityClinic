package com.example.jubileecommunityclinic

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class PreviousActivity: AppCompatActivity() {

    private lateinit var menu:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_previous)

        menu = findViewById(R.id.bottomNavigation)

        //Handling the menu
        menu.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    startActivity(Intent(this@PreviousActivity, HomeActivity::class.java))
                    true
                }
                R.id.menu_booking -> {
                    startActivity(Intent(this@PreviousActivity, BookingActivity::class.java))
                    true
                }
                R.id.menu_upcoming -> {
                    startActivity(Intent(this@PreviousActivity, UpcomingActivity::class.java))
                    true
                }
                R.id.menu_previous -> {
                    startActivity(Intent(this@PreviousActivity, PreviousActivity::class.java))
                    true
                }
                else -> false
            }
        }
        val sharedPrefs: SharedPreferences = getPreferences(Context.MODE_PRIVATE)
        menu.selectedItemId = getSavedSelectedItemId()
    }
    private fun saveSelectedItemId(itemId: Int) {
        sharedPrefs.edit().putInt("selectedItemId", itemId).apply()
    }

    private fun getSavedSelectedItemId(): Int {
        return sharedPrefs.getInt("selectedItemId", R.id.menu_home)
    }
}