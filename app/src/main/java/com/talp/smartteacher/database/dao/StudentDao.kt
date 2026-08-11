package com.talp.smartteacher.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.talp.smartteacher.database.entities.Student
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert
    suspend fun insert(student: Student): Long

    @Update
    suspend fun update(student: Student)

    @Delete
    suspend fun delete(student: Student)

    @Query("SELECT * FROM students WHERE id = :id")
    fun getStudentById(id: Int): Flow<Student?>

    @Query("SELECT * FROM students WHERE code = :code AND teacherId = :teacherId")
    fun getStudentByCode(code: String, teacherId: Int): Flow<Student?>

    @Query("SELECT * FROM students WHERE teacherId = :teacherId ORDER BY name ASC")
    fun getStudentsByTeacher(teacherId: Int): Flow<List<Student>>

    @Query("SELECT * FROM students WHERE teacherId = :teacherId AND classNumber = :classNumber AND section = :section ORDER BY name ASC")
    fun getStudentsByClass(teacherId: Int, classNumber: String, section: String): Flow<List<Student>>

    @Query("DELETE FROM students WHERE teacherId = :teacherId")
    suspend fun deleteAllByTeacher(teacherId: Int)
}
