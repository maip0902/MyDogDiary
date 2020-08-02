package com.example.mydogdiary.activity.Repository

import android.util.Log
import com.example.mydogdiary.activity.Model.Diary
import io.realm.Realm
import io.realm.RealmResults

class RealmDiaryRepository(val realm: Realm = Realm.getDefaultInstance()) {

    public fun create(userId: String?, body: String): Boolean {
        realm.executeTransaction { realm ->
            var diary = Diary()
            Log.d("diary", diary.id)
            diary.userId = userId
            diary.body = body
            Log.d("diary", diary.body)
            realm.copyToRealm(diary)
        }
        return true
    }

    public fun findAll(): RealmResults<Diary> {
        return realm.where(Diary::class.java).findAll()
    }
}