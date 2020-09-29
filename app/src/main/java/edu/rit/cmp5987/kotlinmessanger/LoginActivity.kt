package edu.rit.cmp5987.kotlinmessanger

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //set layout
        setContentView(R.layout.activity_login)

        login_button_login.setOnClickListener {
            val email = email_text_edit_login.text.toString()
            val password = password_text_edit_login.text.toString()

            if(email.isEmpty() || password.isEmpty()) {
                //send error message
                Toast.makeText(this, "Please enter text in email and password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.d("Login", "Attempt to login with email/pw: $email/***")

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (!it.isSuccessful) return@addOnCompleteListener

                    //else if successful
                    Log.d("Login", "Successfully Logged into: ${it.result?.user?.uid}")
                }
                .addOnFailureListener {
                    //failure
                    Log.d("Login", "Failed to login user: ${it.message}")
                    Toast.makeText(this, "Failed to login user: ${it.message}", Toast.LENGTH_SHORT).show()
                }
        }

        back_to_register_text_view.setOnClickListener {
            finish()
        }

    }


}