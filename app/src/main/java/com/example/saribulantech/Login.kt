package com.example.saribulantech

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {
    private lateinit var database : DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        supportActionBar?.hide()
        setContentView(R.layout.activity_login)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        btnLogin.setOnClickListener{
            val password : String = findViewById<EditText>(R.id.inputPassword).text.toString()
            if(password.isNotEmpty()){
                readPassword(password)
            }else{
                Toast.makeText(this, "Password masih kosong!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun readPassword(password:String){
        database = FirebaseDatabase.getInstance().getReference("PASSWORD")
        database.get().addOnSuccessListener {
            if (it.exists()){
                val pw = it.child("password").value
                if(password == pw){
                    val intent = Intent(this, MainMenu::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Password salah!", Toast.LENGTH_SHORT).show()
                }
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show()
        }
    }
}
