package com.example.splashscreenloginsignup

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SecondActivity : AppCompatActivity() {

    lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_second)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val signUpBtn = findViewById<Button>(R.id.signbtn)
        val userName = findViewById<EditText>(R.id.etname)
        val userEmail = findViewById<EditText>(R.id.etEmail)
        val userPassword = findViewById<EditText>(R.id.etPassword)

        val signInlink = findViewById<TextView>(R.id.signinLink)

        signUpBtn.setOnClickListener{
            if(userName.text.isEmpty() || userEmail.text.isEmpty() || userPassword.text.isEmpty()){
                Toast.makeText(this,"Please fill all the fields",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val name = userName.text.toString()
            val email = userEmail.text.toString()
            val password = userPassword.text.toString()

            val user = Users(name,email,password)
            database = FirebaseDatabase.getInstance().getReference("Users")
            database.child(name).setValue(user).addOnSuccessListener {
                userName.text.clear()
                userEmail.text.clear()
                userPassword.text.clear()
                Toast.makeText(this,"User Registered",Toast.LENGTH_SHORT).show()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }

        signInlink.setOnClickListener{
            val intent = Intent(this,SignInActivity::class.java)
            startActivity(intent)
        }

    }
}