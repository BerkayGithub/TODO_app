package com.example.todo_app.db

import android.content.Context
import androidx.room.Room
import com.example.todo_app.dagger.BaseApplication
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideTaskDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        TaskDatabase::class.java,
        "TODO_database"
    ).build()

    @Singleton
    @Provides
    fun provideTaskDAO(db: TaskDatabase) = db.TaskDAO()
}