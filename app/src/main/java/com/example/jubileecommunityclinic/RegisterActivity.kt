package com.example.jubileecommunityclinic

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

class RegisterActivity : AppCompatActivity() {

    private lateinit var editTextEmail: EditText
    private lateinit var editTextPassword: EditText
    private lateinit var editTextConfirmPassword: EditText
    private lateinit var editTextFirstName: EditText
    private lateinit var editTextLastName: EditText
    private lateinit var editTextIDNumber: EditText
    private lateinit var buttonRegister: Button
    private lateinit var textLoginToAccount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        //Views
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPassword = findViewById(R.id.editTextPassword)
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword)
        editTextFirstName = findViewById(R.id.editTextFirstName)
        editTextLastName = findViewById(R.id.editTextLastName)
        editTextIDNumber = findViewById(R.id.editTextIDNumber)
        buttonRegister = findViewById(R.id.buttonRegister)
        textLoginToAccount = findViewById(R.id.textLoginToAccount)

        //OnClickListeners
        textLoginToAccount.setOnClickListener{
            openLogin()
        }

        buttonRegister.setOnClickListener {
            if (isValidRegistration()) {
                registerUser()
            }
        }
    }

    //Displays the Login page
    private fun openLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }

    //Registers a new user with Firebase Authentication
    private fun registerUser() {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        val firstName = editTextFirstName.text.toString().trim()
        val lastName = editTextLastName.text.toString().trim()
        val idNumber = editTextIDNumber.text.toString().trim()

        // Create user in Firebase Authentication
        Firebase.auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // User creation successful, now save additional info in Firestore
                    // Gets the UID of the new user
                    val user = Firebase.auth.currentUser
                    val uid = user?.uid

                    // Creates a hashmap for the document
                    val userInfo = hashMapOf(
                        "firstName" to firstName,
                        "lastName" to lastName,
                        "idNumber" to idNumber
                    )

                    // Sets the document id to the UID of the new user and adds the document to the collection
                    uid?.let {
                        Firebase.firestore.collection("userInfo").document(it)
                            .set(userInfo)
                            .addOnSuccessListener {
                                Log.d("RegisterActivity", "User information saved successfully")
                            }
                            .addOnFailureListener { e ->
                                Log.e("RegisterActivity", "Error saving user information", e)
                            }
                    }

                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                } else {
                    Log.w("RegisterActivity", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        this, "Registration failed. ${task.exception?.localizedMessage}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }



    //Checks that all input fields are valid
    private fun isValidRegistration(): Boolean {
        val email = editTextEmail.text.toString().trim()
        val password = editTextPassword.text.toString().trim()
        val confirmPassword = editTextConfirmPassword.text.toString().trim()
        val firstName = editTextFirstName.text.toString().trim()
        val lastName = editTextLastName.text.toString().trim()
        val idNumber = editTextIDNumber.text.toString().trim()


        // Check if email is a valid email address
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.error = "Invalid email address"
            return false
        }

        //Checks if firstName is empty
        if(firstName==""){
            editTextFirstName.error = "First Name cannot be empty"
        }

        //Checks if lastName is empty
        if(lastName==""){
            editTextLastName.error = "Last Name cannot be empty"
        }

        // Checks if passwords match
        if (password != confirmPassword) {
            editTextConfirmPassword.error = "Passwords must match"
            return false
        } else if (password.isEmpty()) {
            editTextPassword.error = "Password cannot be empty"
            return false
        } else if (!isValidPassword(password)) {
            editTextPassword.error = "Password must be at least 8 characters long with 1 uppercase, 1 lowercase, 1 number and 1 special character."
            return false
        }

        // Check if ID number is exactly 10 digits long
        if (idNumber.length != 13) {
            editTextIDNumber.error = "ID number must be 13 digits"
            return false
        }

        return true
    }

    // Checks if the password meets the required format
    private fun isValidPassword(password: String): Boolean {
        val passwordRegex = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@\$%^&*-]).{8,}\$"
        return password.matches(passwordRegex.toRegex())
    }


}
