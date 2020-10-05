package com.example.todo_app.home

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.todo_app.db.TaskDatabase
import com.example.todo_app.db.TaskRepository
import com.example.todo_app.entities.Task

class HomeViewModel @ViewModelInject constructor(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Task>>
    private val repository: TaskRepository

    init {
        val taskDAO = TaskDatabase.getDatabase(application).TaskDAO()
        repository = TaskRepository(taskDAO)
        readAllData = repository.readAllData
    }
}
