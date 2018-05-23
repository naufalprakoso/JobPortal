package com.fj.jobportal.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fj.jobportal.R
import com.fj.jobportal.model.Job
import kotlinx.android.synthetic.main.item_job.view.*

/**
 * Created by naufalprakoso on 23/05/18.
 */
class JobAdapter(private val context: Context?, private val jobs: List<Job>)
    : RecyclerView.Adapter<JobAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(jobs[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)
            = ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_job, parent, false))

    override fun getItemCount(): Int = jobs.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(job: Job){
            itemView.txt_position.text = job.jobtype
            itemView.txt_name.text = job.companyname
            itemView.txt_salary.text = job.salary


        }
    }

}