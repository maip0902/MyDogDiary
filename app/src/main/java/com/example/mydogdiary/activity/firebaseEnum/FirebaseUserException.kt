package com.example.mydogdiary.activity.firebaseEnum

enum class FirebaseUserException(val exception: String) {
    alreadyUsedEmail("The email address is already in use by another account") {
        override fun returnErrorMessage(): String {
            return "すでに登録されているメールアドレスです"
        }
    },
    invalidEmail("The email address is badly formatted."){
        override fun returnErrorMessage(): String {
            return "正しいメールアドレスの形式で入力してください"

        }
    },
    shortPassword("The given password is invalid. [ Password should be at least 6 characters ]"){
        override fun returnErrorMessage(): String {
            return "パスワードは6文字以上で設定してください"
        }
    },
    cannotLogin("The password is invalid or the user does not have a password.") {
        override fun returnErrorMessage(): String {
            return "メールアドレスもしくはパスワードが間違っています"
        }
    },
    otherwise("") {
        override fun returnErrorMessage(): String {
            return "何らかのエラーが発生しました"
        }
    };

    abstract fun returnErrorMessage() : String
}