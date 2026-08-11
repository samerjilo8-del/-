package com.talp.smartteacher.database.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "grades",
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = ["id"],
            childColumns = ["studentId"],
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
data class Grade(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val studentId: Int,
    val subjectId: Int,
    val oral1: Float? = null,
    val oral2: Float? = null,
    val study1: Float? = null,
    val study2: Float? = null,
    val exam: Float? = null,
    val semester: Int, // 1 or 2
    val total: Float? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val notificationSent: Boolean = false
) {
    fun calculateTotal(): Float? {
        val grades = listOfNotNull(oral1, oral2, study1, study2, exam)
        return if (grades.isNotEmpty()) grades.average().toFloat() else null
    }
}
