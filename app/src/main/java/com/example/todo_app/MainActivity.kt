package com.example.todo_app

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.todo_app.addTask.AddTask
import com.example.todo_app.home.HomeFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get RelativeLayout and FrameLayout from activity layout class
        val relativeLayout = findViewById<RelativeLayout>(R.id.main_relLayout)

        setupActionBarWithNavController(findNavController(R.id.main_frameLayout))
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.main_frameLayout)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}
