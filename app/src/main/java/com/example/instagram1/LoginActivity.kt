package com.example.instagram1

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if(ParseUser.getCurrentUser() != null){
            goToMainActivity()
        }
        findViewById<Button>(R.id.button).setOnClickListener{
            val username = findViewById<EditText>(R.id.ed_username).text.toString()
            val password = findViewById<EditText>(R.id.ed_password).text.toString()
        }
    }


    private fun signUpInBackground(username: String, password: String){
        val user = ParseUser()

        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground{ e ->
            if(e == null){

            } else {
                e.printStackTrace()
            }
        }
    }
    private fun loginUser(username: String, password: String){
        ParseUser.logInInBackground(username, password, ({ user, e ->
            if (user != null) {
                Log.i(TAG, "Successfully logged in user")
                goToMainActivity()
            } else {
                e.printStackTrace()
                Toast.makeText(this, "Error logging in", Toast.LENGTH_SHORT).show()
            }})
        )
    }

    private fun goToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }


    companion object{
       const val TAG = "LoginActivity"
    }
}
