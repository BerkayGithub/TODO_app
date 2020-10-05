package com.example.todo_app.addTask

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo_app.db.TaskDatabase
import com.example.todo_app.db.TaskRepository
import com.example.todo_app.entities.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AddTaskViewModel @ViewModelInject constructor (application: Application) : AndroidViewModel(application) {
    private val readAllData: LiveData<List<Task>>
    private val repository: TaskRepository

    init {
        val taskDAO = TaskDatabase.getDatabase(application).TaskDAO()
        repository = TaskRepository(taskDAO)
        readAllData = repository.readAllData
    }

    fun addTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addTask(task)
        }
    }

}