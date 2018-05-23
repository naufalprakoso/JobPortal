package com.fj.jobportal

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.fragment_create_job.*
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.fragment_create_job.view.*
import org.jetbrains.anko.toast

class CreateJobFragment : Fragment(), View.OnClickListener {

    var database = FirebaseDatabase.getInstance()
    var myRef = database.getReference()

    override fun onClick(v: View?) {
        when(v){
            btn_create -> {
                var user = FirebaseAuth.getInstance().currentUser
                var uid = user?.uid
                var name = edt_name.text.toString()
                var position = edt_position.text.toString()
                var salary = edt_salary.text.toString()
                var type = list_job_type.selectedItem.toString()
                var desc = edt_jobdesc.text.toString()
                var experience = edt_min_experience.text.toString()

                myRef.child("company").child(uid).child("vacancy" + position.get(0) + salary.get(0) + name.get(0))
                        .child("companyName").setValue(name)
                myRef.child("company").child(uid).child("vacancy" + position.get(0) + salary.get(0) + name.get(0))
                        .child("jobPosition").setValue(position)
                myRef.child("company").child(uid).child("vacancy" + position.get(0) + salary.get(0) + name.get(0))
                        .child("salary").setValue(salary)
                myRef.child("company").child(uid).child("vacancy" + position.get(0) + salary.get(0) + name.get(0))
                        .child("jobType").setValue(type)
                myRef.child("company").child(uid).child("vacancy" + position.get(0) + salary.get(0) + name.get(0))
                        .child("jobDesc").setValue(desc)
                myRef.child("company").child(uid).child("vacancy" + position.get(0) + salary.get(0) + name.get(0))
                        .child("jobExperience").setValue(experience)

                context?.toast("Insert succesful")

                var mainFragment = MainFragment()
                activity?.supportFragmentManager?.inTransaction {
                    replace(R.id.container_frame, mainFragment)
                }
            }
        }
    }

    inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_create_job, container, false)

        val adapterType = ArrayAdapter.createFromResource(
                context,
                R.array.type_array,
                android.R.layout.simple_spinner_item)
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        view.list_job_type.setAdapter(adapterType)

        view.btn_create.setOnClickListener(this)

        return view
    }
}
