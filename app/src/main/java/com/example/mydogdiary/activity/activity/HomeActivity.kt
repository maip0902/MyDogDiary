package com.example.mydogdiary.activity.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.example.mydogdiary.R
import com.example.mydogdiary.activity.ListObject.TimelineDiary
import com.example.mydogdiary.activity.Repository.RealmDiaryRepository
import com.example.mydogdiary.activity.adapter.TimeLineAdapter
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

        val diary = RealmDiaryRepository()
        val diaries = diary.findAll()

        val timelineDiary: ArrayList<TimelineDiary> = arrayListOf()

        for (diary in diaries) {
            timelineDiary.add(TimelineDiary(diary.body))
            Log.d("diaryList", diary.id)
            Log.d("diaryList", diary.body)
        }
        val adapter = TimeLineAdapter(this, timelineDiary)
        listView.adapter = adapter

    }
}