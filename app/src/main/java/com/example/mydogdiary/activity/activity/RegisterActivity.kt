package com.example.mydogdiary.activity.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mydogdiary.R
import com.example.mydogdiary.activity.firebaseEnum.FirebaseUserException
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

                        when(task.exception?.message) {
                            "The email address is already in use by another account." -> {
                                errorMessage.text = FirebaseUserException.alreadyUsedEmail.returnErrorMessage()
                            }
                            "The email address is badly formatted." -> {
                                errorMessage.text = FirebaseUserException.invalidEmail.returnErrorMessage()
                            }
                            "The given password is invalid. [ Password should be at least 6 characters ]" -> {
                                errorMessage.text = FirebaseUserException.shortPassword.returnErrorMessage()
                            }
                        }
                    }
                }
        }

    }
}