package com.example.mydogdiary.activity.activity

import android.content.Intent
import android.os.Bundle
import com.example.mydogdiary.R
import com.example.mydogdiary.activity.middleware.AuthenticateActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity: AuthenticateActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.isAuthenticated()
        setContentView(R.layout.activity_home)
        loginUser.text = this.displayName
        saveDiary.setOnClickListener {
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }

    }
}