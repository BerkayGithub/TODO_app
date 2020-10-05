package com.example.todo_app.updateTask

import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs

import com.example.todo_app.R
import com.example.todo_app.entities.Task
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateTask : Fragment() {

    private var title: String? = null
    private var time: String? = null
    private var price: Double? = null
    private var body: String? = null

    private val viewModel: UpdateTaskViewModel by viewModels()

    private val args by navArgs<UpdateTaskArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_update_task, container, false)
        //viewModel = ViewModelProviders.of(this).get(UpdateTaskViewModel::class.java)

        setHasOptionsMenu(true)

        val linLayout = view.findViewById<LinearLayout>(R.id.update_Task_linLayout)

        val lp_editText = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lp_editText.setMargins(20,30,20,0)

        val editText_title = EditText(context)
        editText_title.setLayoutParams(lp_editText)
        editText_title.setText(args.task.title)
        editText_title.setBackgroundColor(resources.getColor(R.color.white))
        editText_title.inputType = InputType.TYPE_CLASS_TEXT

        val editText_time = EditText(context)
        editText_time.setLayoutParams(lp_editText)
        editText_time.setText(args.task.time)
        editText_time.setBackgroundColor(resources.getColor(R.color.white))
        editText_time.inputType = InputType.TYPE_DATETIME_VARIATION_TIME

        val editText_price = EditText(context)
        editText_price.setLayoutParams(lp_editText)
        editText_price.setText(args.task.price.toString())
        editText_price.setBackgroundColor(resources.getColor(R.color.white))
        editText_price.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL

        val editText_body = EditText(context)
        editText_body.setLayoutParams(lp_editText)
        editText_body.setText(args.task.body)
        editText_body.setBackgroundColor(resources.getColor(R.color.white))
        editText_body.setLines(3)
        editText_body.inputType = InputType.TYPE_CLASS_TEXT

        val btn_finish = Button(context)
        btn_finish.setBackgroundColor(resources.getColor(R.color.colorPrimary))
        btn_finish.setTextColor(resources.getColor(R.color.white))
        btn_finish.setText("Finish")
        val lp_btn_finish = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lp_btn_finish.setMargins(40,60,40,20)
        btn_finish.setLayoutParams(lp_btn_finish)

        btn_finish.setOnClickListener {
            title = editText_title.text.toString()
            time = editText_time.text.toString()
            price = editText_price.text.toString().toDouble()
            body = editText_body.text.toString()
            updateTask(title!!,time!!,price!!,body!!)
        }

        linLayout.addView(editText_title)
        linLayout.addView(editText_time)
        linLayout.addView(editText_price)
        linLayout.addView(editText_body)
        linLayout.addView(btn_finish)
        return view
    }


    private fun updateTask(title: String, time: String, price: Double, body: String) {
        val updatedTask = Task(args.task.taskID, title, time, price, body)
        viewModel.updateTask(updatedTask)
        Toast.makeText(requireContext(),"Successfully Updated!", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_updateTask_to_homeFragment)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.update_task_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.item_delete_Task){
            deleteTask()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTask() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes"){_, _ ->
            viewModel.deleteTask(args.task)
            Toast.makeText(requireContext(), "Successfully deleted.", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateTask_to_homeFragment)
        }
        builder.setNegativeButton("No"){_, _ -> }
        builder.setTitle("Delete ${args.task.title}?")
        builder.setMessage("Are you sure you want to delete ${args.task.title}?")
        builder.create().show()

    }

}
