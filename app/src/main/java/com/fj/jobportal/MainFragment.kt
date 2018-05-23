package com.fj.jobportal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fj.jobportal.adapter.JobAdapter
import com.fj.jobportal.model.Job
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MainFragment : Fragment(), View.OnClickListener {

    lateinit var databaseReference : DatabaseReference

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

        databaseReference = FirebaseDatabase.getInstance().reference
        val jobs = java.util.ArrayList<Job>()

        view.rv_data.setHasFixedSize(true)
        view.rv_data.layoutManager = GridLayoutManager(context, 2)

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.child("company").child(user?.uid).children) {
                    val vacancy = dataSnapshot.getValue<Job>(Job::class.java)
                    vacancy?.let { jobs.add(it) }
                }

                view.rv_data.adapter = JobAdapter(context, jobs){
                    context?.startActivity<JobDetailActivity>(
                            "name" to "${it.companyName}",
                            "type" to it.jobType,
                            "desc" to "${it.jobDesc}"
                    )
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {

            }
        })

        return view
    }
}
