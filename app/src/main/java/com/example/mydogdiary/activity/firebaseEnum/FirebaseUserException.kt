package com.example.mydogdiary.activity.firebaseEnum

enum class FirebaseUserException() {
    invalidEmail {
        override fun returnErrorMessage(): String {
            return "すでに登録されているメールアドレスです"
        }
    },
    shortPassword{
        override fun returnErrorMessage(): String {
            return "正しいメーリアドレスの形式で入力してください"

        }
    },
    alreadyUsedEmail{
        override fun returnErrorMessage(): String {
            return "パスワードは6文字以上で設定してください"
        }
    };

    abstract fun returnErrorMessage() : String
}