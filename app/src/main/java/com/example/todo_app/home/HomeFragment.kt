package com.example.todo_app.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo_app.R
import com.example.todo_app.adapter.TaskListAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var relativeLayout: RelativeLayout
    @Inject lateinit var viewadapter: TaskListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view : View = inflater.inflate(R.layout.home_fragment, container, false)

        relativeLayout = view.findViewById(R.id.home_rel_layout)

        val addBtn = ImageView(this.context)
        addBtn.setImageResource(R.drawable.ic_add_circle_black_24dp)
        addBtn.setColorFilter(resources.getColor(R.color.colorPrimary))
        val lp_addBtn = RelativeLayout.LayoutParams(
            125,
            125
        )
        lp_addBtn.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        lp_addBtn.addRule(RelativeLayout.ALIGN_PARENT_END)
        lp_addBtn.setMargins(0,0,50,50)
        addBtn.setLayoutParams(lp_addBtn)
        addBtn.setOnClickListener(View.OnClickListener {
            openAddTaskFragment()
        })
        relativeLayout.addView(addBtn)

        val recyclerview = RecyclerView(requireContext())
        val recyclerview_lp = RelativeLayout.LayoutParams(
            RelativeLayout.LayoutParams.MATCH_PARENT,
            RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        recyclerview.setLayoutParams(recyclerview_lp)

        var viewManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recyclerview.apply {
            setHasFixedSize(true)

            layoutManager = viewManager

            adapter = viewadapter
        }
        relativeLayout.addView(recyclerview)

        viewModel.readAllData.observe(viewLifecycleOwner, Observer {tasklist ->
            viewadapter.setData(tasklist)
        })
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun openAddTaskFragment(){
        findNavController().navigate(R.id.action_homeFragment_to_addTask)
    }
}
