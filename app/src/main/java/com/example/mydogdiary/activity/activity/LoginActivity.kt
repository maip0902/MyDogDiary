package com.example.mydogdiary.activity.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mydogdiary.R
import com.example.mydogdiary.activity.firebaseEnum.FirebaseUserException
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        auth = FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // TODO::リファクタしたい
        saveDiary.setOnClickListener {
            errorMessage.text = ""

            if(loginEmail.text.toString() == "" && loginPassword.text.toString() == "") {
                errorMessage.text = "メールアドレスとパスワードを入力してください"
            }
            else {
                auth.signInWithEmailAndPassword(loginEmail.text.toString(), loginPassword.text.toString())
                    .addOnCompleteListener() { task ->
                        if(task.isSuccessful) {
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                        }

                        when(task.exception?.message) {
                            FirebaseUserException.cannotLogin.exception
                                -> errorMessage.text = FirebaseUserException.cannotLogin.returnErrorMessage()
                            FirebaseUserException.invalidEmail.exception
                                -> errorMessage.text = FirebaseUserException.invalidEmail.returnErrorMessage()
                            else
                                -> errorMessage.text = FirebaseUserException.otherwise.returnErrorMessage()
                        }
                    }
            }
        }
    }
}