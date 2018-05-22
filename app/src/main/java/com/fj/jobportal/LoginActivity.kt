package com.fj.jobportal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class LoginActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        btn_register.setOnClickListener(this)
        btn_login.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v){
            btn_register -> {
                var email = edt_email.text.toString()
                var password = edt_password.text.toString()

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                startActivity<HomeActivity>()
                                finish()
                                toast("Success")
                            } else {
                                toast("User registered")
                            }
                        }
            }

            btn_login -> {
                var email = edt_email.text.toString()
                var password = edt_password.text.toString()

                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                startActivity<HomeActivity>()
                                finish()
                                toast("Success")
                            } else {
                                toast("Failed")
                            }
                        }
            }
        }
    }
}
