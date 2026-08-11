package com.talp.smartteacher.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.talp.smartteacher.database.entities.Exam
import kotlinx.coroutines.flow.Flow

@Dao
interface ExamDao {
    @Insert
    suspend fun insert(exam: Exam): Long

    @Update
    suspend fun update(exam: Exam)

    @Delete
    suspend fun delete(exam: Exam)

    @Query("SELECT * FROM exams WHERE id = :id")
    fun getExamById(id: Int): Flow<Exam?>

    @Query("SELECT * FROM exams WHERE teacherId = :teacherId ORDER BY examDate ASC")
    fun getExamsByTeacher(teacherId: Int): Flow<List<Exam>>

    @Query("SELECT * FROM exams WHERE teacherId = :teacherId AND semester = :semester ORDER BY examDate ASC")
    fun getExamsByTeacherAndSemester(teacherId: Int, semester: Int): Flow<List<Exam>>

    @Query("SELECT * FROM exams WHERE subjectId = :subjectId ORDER BY examDate ASC")
    fun getExamsBySubject(subjectId: Int): Flow<List<Exam>>

    @Query("SELECT * FROM exams WHERE type = :type ORDER BY examDate ASC")
    fun getExamsByType(type: String): Flow<List<Exam>>

    @Query("DELETE FROM exams WHERE teacherId = :teacherId")
    suspend fun deleteAllByTeacher(teacherId: Int)
}
