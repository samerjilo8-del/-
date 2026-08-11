package com.talp.smartteacher.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "subjects",
    foreignKeys = [
        ForeignKey(
            entity = Teacher::class,
            parentColumns = ["id"],
            childColumns = ["teacherId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val teacherId: Int,
    val name: String,
    val sections: String, // JSON format or comma-separated
    val createdAt: Long = System.currentTimeMillis()
)
