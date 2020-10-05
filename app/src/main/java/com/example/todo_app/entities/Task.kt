package com.example.todo_app.entities


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "task_table")
class Task (
    @PrimaryKey(autoGenerate = true) var taskID: Int,
    var title: String,
    var time: String,
    var price: Double,
    var body: String
): Parcelable

