package com.example.todo_app.updateTask

import android.app.Application
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.todo_app.db.TaskDatabase
import com.example.todo_app.db.TaskRepository
import com.example.todo_app.entities.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateTaskViewModel @ViewModelInject constructor (
    application: Application,
    private val repository: TaskRepository
) : AndroidViewModel(application) {
    private val readAllData: LiveData<List<Task>> = repository.readAllData
    //private val repository: TaskRepository

    init {
        //val taskDAO = TaskDatabase.getDatabase(application).TaskDAO()
        //repository = TaskRepository(taskDAO)
    }

    fun updateTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateTask(task)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteTask(task)
        }
    }


}