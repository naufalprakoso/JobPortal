package com.fj.jobportal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.jetbrains.anko.toast

class MainFragment : Fragment(), View.OnClickListener {

    override fun onClick(v: View?) {
        when(v){
            btn_verify -> {
                var mAuth = FirebaseAuth.getInstance()
                val user = mAuth.getCurrentUser()

                if (user != null) {
                    user.sendEmailVerification()
                            .addOnCompleteListener({ task ->
                                if (task.isSuccessful) {
                                    context?.toast("Verification email sent to " + user.getEmail()!!)
                                } else {
                                    context?.toast("Verification email failed")
                                }
                            })
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_main, container, false)

        var user = FirebaseAuth.getInstance().currentUser
        var emailVerified = user?.isEmailVerified

        if (emailVerified!!){
            view.cv_status.visibility = View.GONE
        }else{
            view.cv_status.visibility = View.VISIBLE
        }

        view.btn_verify.setOnClickListener(this)

        return view
    }
}
