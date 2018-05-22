package com.fj.jobportal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth : FirebaseAuth

    private lateinit var email : String
    private lateinit var password : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        btn_register.setOnClickListener(this)
        btn_login.setOnClickListener(this)

        email = edt_email.text.toString()
        password = edt_password.text.toString()
    }

    override fun onClick(v: View?) {
        when(v){
            btn_register -> {
                toast("Button Register Clicked")

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
//                                val user = mAuth.currentUser
//                                updateUI(user)
                                toast("Success")
                            } else {
                                toast("Failed")
                            }
                        }
            }

            btn_login -> {
                toast("Button Login Clicked")

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
//                                val user = mAuth.currentUser
                                toast("Success")
//                                updateUI(user)
                            } else {
                                toast("Failed")
//                                updateUI(null)
                            }
                        }
            }
        }
    }

}
