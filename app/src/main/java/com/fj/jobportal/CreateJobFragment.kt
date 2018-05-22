package com.fj.jobportal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_create_job.*
import com.google.firebase.database.FirebaseDatabase

class CreateJobFragment : Fragment(), View.OnClickListener {

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference("message")

    override fun onClick(v: View?) {
        when(v){
            btn_create -> {
                var user = FirebaseAuth.getInstance().currentUser
                var email = user?.email
                var name = edt_name.text.toString()
                var position = edt_position.text.toString()
                var salary = edt_salary.text.toString()
                var type = list_job_type.selectedItem.toString()
                var desc = edt_jobdesc.text.toString()
                var experience = edt_min_experience.text.toString()

                myRef.child("company").child(email).child("vacancy")
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_create_job, container, false)
        return view
    }
}
