package com.example.mydogdiary.activity.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mydogdiary.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            for (profile in it.providerData) {
                val loginUserDisplayName = profile.displayName
                displayName.text = loginUserDisplayName
            }
        }

    }
}