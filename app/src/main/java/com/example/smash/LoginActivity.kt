package com.example.smash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.smash.Controller.DashboardActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_create_user.*
import kotlinx.android.synthetic.main.activity_create_user.createEmailTxt
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
// Initialize Firebase Auth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()
    }


    fun loginLoginBtnClicked(view: View) {


        val email = loginEmailTxt.text.toString()
        val password = loginPasswordTxt.text.toString()

        if (loginEmailTxt.text.toString().isEmpty()) {
            loginEmailTxt.error = "please enter your email"
            loginEmailTxt.requestFocus()
            return
        }

        if (loginPasswordTxt.text.toString().isEmpty()) {
            loginPasswordTxt.error = "please enter your password"
            loginPasswordTxt.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    updateUI(null)

                }


            }
    }


    fun loginCreateUserBtnClicked(view: View) {
        val createUserIntent = Intent(this, CreateUserActivity::class.java)
        startActivity((createUserIntent))
        finish()
    }


    fun createUserClicked(view: View) {
        val createUserIntent = Intent(this, LoginActivity::class.java)
        startActivity((createUserIntent))
        finish()
    }


    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        // updateUI(currentUser)
    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            if (currentUser.isEmailVerified){
            startActivity(Intent(this, DashboardActivity::class.java))

        } else{
                Toast.makeText(
                    baseContext, "Please verify your e-mail address.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

            else {
            // If sign in fails, display a message to the user.

            Toast.makeText(
                baseContext, "Authentication failed.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}