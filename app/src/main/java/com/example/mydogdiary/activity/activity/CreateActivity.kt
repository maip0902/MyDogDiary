package com.example.mydogdiary.activity.activity

import android.os.Bundle
import com.example.mydogdiary.R
import com.example.mydogdiary.activity.Repository.RealmDiaryRepository
import com.example.mydogdiary.activity.middleware.AuthenticateActivity
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AuthenticateActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.isAuthenticated()
        setContentView(R.layout.activity_create)

        val diary = RealmDiaryRepository()

        saveDiary.setOnClickListener {
            diary.create(this.uid, diaryBody.text.toString())
        }
    }
}
