package com.talp.smartteacher.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "teachers")
data class Teacher(
    @PrimaryKey
    val id: Int = 1,
    val name: String,
    val username: String,
    val password: String,
    val classNumber: String = "",
    val section: String = "",
    val createdAt: Long = System.currentTimeMillis()
)
