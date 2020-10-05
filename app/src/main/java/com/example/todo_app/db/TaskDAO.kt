package com.example.todo_app.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.todo_app.dagger.BaseApplication
import com.example.todo_app.entities.Task
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Dao
interface TaskDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addTask(task: Task)

    @Query("SELECT * FROM task_table ORDER BY taskID ASC")
    fun readTaskData(): LiveData<List<Task>>

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deletetask(task: Task)

}