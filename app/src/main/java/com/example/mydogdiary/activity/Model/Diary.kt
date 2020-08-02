package com.example.mydogdiary.activity.Model
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.Required
import java.util.*

open class Diary(): RealmObject() {

    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    @Required
    var body: String = ""
    @Required
    var userId: String? = ""

    public fun getDiaryBody(): String { return this.body}

    public fun getDiaryUserId(): String? { return this.userId}
}