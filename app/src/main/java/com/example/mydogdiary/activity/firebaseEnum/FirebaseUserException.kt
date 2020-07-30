package com.example.mydogdiary.activity.firebaseEnum

enum class FirebaseUserException(val exception: String) {
    invalidEmail("The email address is already in use by another account") {
        override fun returnErrorMessage(): String {
            return "すでに登録されているメールアドレスです"
        }
    },
    shortPassword("The email address is badly formatted."){
        override fun returnErrorMessage(): String {
            return "正しいメールアドレスの形式で入力してください"

        }
    },
    alreadyUsedEmail("The given password is invalid. [ Password should be at least 6 characters ]"){
        override fun returnErrorMessage(): String {
            return "パスワードは6文字以上で設定してください"
        }
    };

    abstract fun returnErrorMessage() : String
}