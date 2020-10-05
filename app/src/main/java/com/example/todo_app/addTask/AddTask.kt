package com.example.todo_app.addTask

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.setMargins
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController

import com.example.todo_app.R
import com.example.todo_app.entities.Task
import com.example.todo_app.home.HomeFragment
import com.example.todo_app.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.list_row.*

@AndroidEntryPoint
class AddTask : Fragment() {

    private val viewModel: AddTaskViewModel by viewModels()

    private var title: String? = null
    private var time: String? = null
    private var price: Double? = null
    private var body: String? = null
    private lateinit var linLayout: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view : View = inflater.inflate(R.layout.fragment_add_task, container, false)
        //viewModel = ViewModelProviders.of(this).get(AddTaskViewModel::class.java)

        linLayout = view.findViewById(R.id.add_Task_linLayout)

        val lp_editText = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        lp_editText.setMargins(20,30,20,0)

        val editText_title = EditText(context)
        editText_title.setLayoutParams(lp_editText)
        editText_title.setHint("Enter Title")
        editText_title.setBackgroundColor(resources.getColor(R.color.white))
        editText_title.inputType = InputType.TYPE_CLASS_TEXT

        val editText_time = EditText(context)
        editText_time.setLayoutParams(lp_editText)
        editText_time.setHint("Enter Time")
        editText_time.setBackgroundColor(resources.getColor(R.color.white))
        editText_time.inputType = InputType.TYPE_DATETIME_VARIATION_TIME

        val editText_price = EditText(context)
        editText_price.setLayoutParams(lp_editText)
        editText_price.setHint("Enter Price")
        editText_price.setBackgroundColor(resources.getColor(R.color.white))
        editText_price.inputType = InputType.TYPE_NUMBER_FLAG_DECIMAL

        val editText_body = EditText(context)
        editText_body.setLayoutParams(lp_editText)
        editText_body.setHint("Task Description")
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
            addTask(title!!,time!!,price!!,body!!)
        }

        linLayout.addView(editText_title)
        linLayout.addView(editText_time)
        linLayout.addView(editText_price)
        linLayout.addView(editText_body)
        linLayout.addView(btn_finish)

        return view
    }

    private fun addTask(title: String, time: String, price: Double, body: String) {
        val task = Task(0,title,time, price, body)
        viewModel.addTask(task)
        findNavController().navigate(R.id.action_addTask_to_homeFragment)
    }
}

