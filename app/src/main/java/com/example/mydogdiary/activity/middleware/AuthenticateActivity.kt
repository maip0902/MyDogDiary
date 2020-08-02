package com.example.mydogdiary.activity.middleware

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mydogdiary.activity.activity.MainActivity
import com.google.firebase.auth.FirebaseAuth

open class AuthenticateActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    var uid: String? = ""
    var displayName: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
    }

    public fun isAuthenticated(): Boolean {
        val user = auth.currentUser

        if(user == null) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        uid = user?.uid
        displayName = user?.displayName

        return true
    }
}
