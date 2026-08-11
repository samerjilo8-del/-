package com.talp.smartteacher.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.talp.smartteacher.database.entities.Teacher
import kotlinx.coroutines.flow.Flow

@Dao
interface TeacherDao {
    @Insert
    suspend fun insert(teacher: Teacher): Long

    @Update
    suspend fun update(teacher: Teacher)

    @Delete
    suspend fun delete(teacher: Teacher)

    @Query("SELECT * FROM teachers WHERE id = :id")
    fun getTeacherById(id: Int): Flow<Teacher?>

    @Query("SELECT * FROM teachers WHERE username = :username")
    fun getTeacherByUsername(username: String): Flow<Teacher?>

    @Query("SELECT * FROM teachers LIMIT 1")
    fun getCurrentTeacher(): Flow<Teacher?>

    @Query("UPDATE teachers SET password = :newPassword WHERE id = :id")
    suspend fun updatePassword(id: Int, newPassword: String)

    @Query("UPDATE teachers SET classNumber = :classNumber, section = :section WHERE id = :id")
    suspend fun updateClassInfo(id: Int, classNumber: String, section: String)
}
