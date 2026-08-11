package com.talp.smartteacher.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "exams",
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
data class Exam(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val teacherId: Int,
    val subjectId: Int,
    val title: String,
    val examDate: Long,
    val semester: Int, // 1 or 2
    val type: String, // "exam" or "study"
    val createdAt: Long = System.currentTimeMillis()
)
