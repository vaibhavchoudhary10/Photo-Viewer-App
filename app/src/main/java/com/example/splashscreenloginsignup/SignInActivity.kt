package com.example.splashscreenloginsignup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {

    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sign_in)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signInButton = findViewById<Button>(R.id.signInbtn)
        val userName = findViewById<EditText>(R.id.etUsername)
        val userPassword = findViewById<EditText>(R.id.etUserpassword)

        signInButton.setOnClickListener {
            val name = userName.text.toString()
            val password = userPassword.text.toString()
            if (name.isNotEmpty() && password.isNotEmpty()) {
                readData(name, password)
            }else{
                Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(name: String, password: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        databaseReference.child(name).get().addOnSuccessListener {
            if (it.exists()) {
                val getPassword = it.child("password").value
                if (password == getPassword) {
                    val alerter = AlertDialog.Builder(this)
                    alerter.setTitle("Login Successful")
                    alerter.setMessage("Welcome $name")
                    alerter.setPositiveButton("OK") { _, _ ->
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    }.show()
                } else {
                    Toast.makeText(this, "Password is Incorrect", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "User does not exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Error Occurred", Toast.LENGTH_SHORT).show()
        }
    }
}