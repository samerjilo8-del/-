package com.talp.smartteacher.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "homework",
    foreignKeys = [
        ForeignKey(
            entity = Teacher::class,
            parentColumns = ["id"],
            childColumns = ["teacherId"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = Subject::class,
            parentColumns = ["id"],
            childColumns = ["subjectId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Homework(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val teacherId: Int,
    val subjectId: Int,
    val title: String,
    val description: String,
    val dueDate: Long,
    val createdAt: Long = System.currentTimeMillis(),
    val notificationSent: Boolean = false
)
