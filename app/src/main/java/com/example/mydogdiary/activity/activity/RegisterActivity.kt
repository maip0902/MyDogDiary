package com.example.mydogdiary.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mydogdiary.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {

        /*
        1. email,passwordがnullだったらバリデーションエラー返す
        2. emailがメールアドレスの形じゃなかったらエラー
        3. 同じメールアドレスですでに登録されてたらエラー
        4. displayNameは10文字以内
         */
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        registerButton.setOnClickListener {
            val registerEmail = registerEmail.text.toString()
            val registerPassword = registerPassword.text.toString()
            Log.d("text", registerEmail)
            Log.d("text", registerPassword)

            auth.createUserWithEmailAndPassword(registerEmail, registerPassword)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("auth", "createUserWithEmail:success")
                        val user = auth.currentUser

                        // userのprofile設定
                        val displayName = registerDisplayName.text.toString()
                        val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(displayName).build()

                        user?.updateProfile(profileUpdates)
                            ?.addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Log.d("text", "update displayname success")

                                    val intent = Intent(this, HomeActivity::class.java)
                                    startActivity(intent)

                                }
                            }

                    } else {
                        Log.d("error", task.exception?.message)
                        if (task.exception?.message == "The email address is already in use by another account.") {
                            Log.d("error", "すでに登録されているメールアドレスです")
                        }

                        if(task.exception?.message == "The email address is badly formatted.") {
                            Log.d("error", "正しいメーリアドレスの形式で入力してください")
                        }

                        if(task.exception?.message == "The given password is invalid. [ Password should be at least 6 characters ]") {
                            Log.d("error", "パスワードは6文字以上で設定してください")
                        }
                    }
                }
        }

    }
}