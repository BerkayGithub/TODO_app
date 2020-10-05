package com.example.todo_app.adapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.ListFragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app.R
import com.example.todo_app.addTask.AddTask
import com.example.todo_app.databinding.ListRowBinding
import com.example.todo_app.entities.Task
import com.example.todo_app.home.HomeFragment
import com.example.todo_app.home.HomeFragmentDirections
import com.example.todo_app.updateTask.UpdateTask
import kotlinx.android.synthetic.main.list_row.view.*
import javax.inject.Inject

class TaskListAdapter @Inject constructor() : RecyclerView.Adapter<TaskListAdapter.ViewHolder>(){

    private var todo_list = emptyList<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_row,
                parent,
                false
            )
        )

    override fun getItemCount(): Int {
        return todo_list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.listRowBinding.task = todo_list[position]

        holder.itemView.row_const.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToUpdateTask(todo_list[position])
            holder.itemView.findNavController().navigate(action)
        }


    }

    class ViewHolder(
        val listRowBinding: ListRowBinding
    ) : RecyclerView.ViewHolder(listRowBinding.root)

    fun setData(tasklist: List<Task>){
        this.todo_list = tasklist
        notifyDataSetChanged()
    }
}
