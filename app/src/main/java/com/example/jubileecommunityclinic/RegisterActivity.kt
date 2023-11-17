package com.example.jubileecommunityclinic

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextFirstName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var editTextIDNumber: EditText
    private lateinit var buttonRegister: Button
    private lateinit var textLoginToAccount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Firebase
        val db = Firebase.firestore
        val auth = Firebase.auth

        //Views
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextFirstName = findViewById(R.id.editTextFirstName)
        editTextLastName = findViewById(R.id.editTextLastName)
        editTextIDNumber = findViewById(R.id.editTextIDNumber)
        buttonRegister = findViewById(R.id.buttonRegister)
        textLoginToAccount = findViewById(R.id.textLoginToAccount)

        textLoginToAccount.setOnClickListener{
            openLogin()
        }
    }

    //Displays the Login page
    private fun openLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

}
