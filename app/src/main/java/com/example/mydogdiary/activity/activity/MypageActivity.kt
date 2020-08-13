package com.example.mydogdiary.activity.activity

import android.os.Bundle
import android.util.Log
import com.example.mydogdiary.R
import com.example.mydogdiary.activity.ListObject.MyDiary
import com.example.mydogdiary.activity.Repository.RealmDiaryRepository
import com.example.mydogdiary.activity.adapter.MypageAdapter
import com.example.mydogdiary.activity.middleware.AuthenticateActivity
import kotlinx.android.synthetic.main.activity_mypage.*

class MypageActivity : AuthenticateActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.isAuthenticated()
        setContentView(R.layout.activity_mypage)

        Log.d("userID", uid)
        Log.d("userID", "ok")
        val diary = RealmDiaryRepository()
        val diaries = diary.findByUserId(uid)
        Log.d("userID", uid)

        val myDiary: ArrayList<MyDiary> = arrayListOf()

        for (diary in diaries) {
            myDiary.add(MyDiary(diary.body))
            Log.d("diaryList", diary.id)
            Log.d("diaryList", diary.body)
        }
        val adapter = MypageAdapter(this, myDiary)
        myListView.adapter = adapter
    }
}
