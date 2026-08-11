package com.talp.smartteacher.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "students",
    foreignKeys = [
        ForeignKey(
            entity = Teacher::class,
            parentColumns = ["id"],
            childColumns = ["teacherId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Student(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val teacherId: Int,
    val name: String,
    val code: String,
    val classNumber: String,
    val section: String,
    val createdAt: Long = System.currentTimeMillis()
)
