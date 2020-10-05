package com.example.todo_app.db

import androidx.lifecycle.LiveData
import com.example.todo_app.entities.Task
import javax.inject.Inject

class TaskRepository @Inject constructor(
    private val taskDAO: TaskDAO
) {
    val readAllData: LiveData<List<Task>> = taskDAO.readTaskData()

    suspend fun addTask(task: Task){
        taskDAO.addTask(task)
    }

    suspend fun updateTask(task: Task){
        taskDAO.updateTask(task)
    }

    suspend fun deleteTask(task: Task){
        taskDAO.deletetask(task)
    }

}