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

            // TODO::try,catchのリファクタ
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
                        when(task.exception?.message) {
                            FirebaseUserException.alreadyUsedEmail.exception ->
                                errorMessage.text = FirebaseUserException.alreadyUsedEmail.returnErrorMessage()
                            FirebaseUserException.invalidEmail.exception ->
                                errorMessage.text = FirebaseUserException.invalidEmail.returnErrorMessage()
                            FirebaseUserException.shortPassword.exception ->
                                errorMessage.text = FirebaseUserException.shortPassword.returnErrorMessage()
                            else ->
                                errorMessage.text = FirebaseUserException.otherwise.returnErrorMessage()
                        }
                    }
                }
        }

    }
}