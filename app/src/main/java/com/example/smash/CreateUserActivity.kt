package com.example.smash

//import Services.AuthService

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_create_user.*


class CreateUserActivity : AppCompatActivity() {

    private lateinit var auth:FirebaseAuth


   // var userAvatar="profileDefault"
    //var avatarColor="[0.5, 0.5, 0.5, 1]"// rgb, alfa

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)
        auth=FirebaseAuth.getInstance()
    }
    /*
    fun generateUserAvatar(view: View){
        val random=Random()
        val color=random.nextInt(2)
        val avatar=random.nextInt(28)

        if (color==0){
            userAvatar="light$avatar"

        }else{
            userAvatar="dark$avatar"
        }

        val resourceId=resources.getIdentifier(userAvatar, "drawable", packageName)
        createAvatarImageView.setImageResource(resourceId)


    }*/



   /* fun generateColorClicked(view: View){
        val random=Random()
        val r=random.nextInt(255)
        val g=random.nextInt(255)
        val b=random.nextInt(255)

        createAvatarImageView.setBackgroundColor(Color.rgb(r, g, b))

        //convert color
        val savedR=r.toDouble()/255
        val savedG=g.toDouble()/255
        val savedB=b.toDouble()/255

        avatarColor="[$savedR,$savedG,$savedB,1]"
        println(avatarColor)
    }*/



    fun createUserClicked(view: View) {

        //val userName = createUserNameTxt.text.toString()
        val email = createEmailTxt.text.toString()
        val password = createPasswordTxt.text.toString()



        if(createEmailTxt.text.toString().isEmpty()){
            createEmailTxt.error="please enter your email"
            createEmailTxt.requestFocus()
            return
        }

        if(createPasswordTxt.text.toString().isEmpty()){
            createPasswordTxt.error="please enter your password"
            createPasswordTxt.requestFocus()
            return
        }

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user :FirebaseUser? = auth.currentUser

                    user?.sendEmailVerification()
                        ?.addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                startActivity(Intent(this, LoginActivity::class.java))
                                finish()
                            }
                        }


                } else {
                   Toast.makeText(
                       baseContext,
                       "sign up failed.Try again after some time",
                       Toast.LENGTH_SHORT
                   ).show()

                }

                // ...
            }

    }


}