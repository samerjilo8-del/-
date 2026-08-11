package com.talp.smartteacher.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.talp.smartteacher.database.entities.Grade
import kotlinx.coroutines.flow.Flow

@Dao
interface GradeDao {
    @Insert
    suspend fun insert(grade: Grade): Long

    @Update
    suspend fun update(grade: Grade)

    @Delete
    suspend fun delete(grade: Grade)

    @Query("SELECT * FROM grades WHERE id = :id")
    fun getGradeById(id: Int): Flow<Grade?>

    @Query("SELECT * FROM grades WHERE studentId = :studentId ORDER BY semester ASC")
    fun getGradesByStudent(studentId: Int): Flow<List<Grade>>

    @Query("SELECT * FROM grades WHERE studentId = :studentId AND semester = :semester ORDER BY subjectId ASC")
    fun getGradesByStudentAndSemester(studentId: Int, semester: Int): Flow<List<Grade>>

    @Query("SELECT * FROM grades WHERE subjectId = :subjectId AND semester = :semester ORDER BY studentId ASC")
    fun getGradesBySubjectAndSemester(subjectId: Int, semester: Int): Flow<List<Grade>>

    @Query("SELECT * FROM grades WHERE notificationSent = 0 ORDER BY createdAt ASC")
    fun getPendingNotifications(): Flow<List<Grade>>

    @Query("UPDATE grades SET notificationSent = 1 WHERE id = :id")
    suspend fun markNotificationSent(id: Int)

    @Query("DELETE FROM grades WHERE studentId = :studentId")
    suspend fun deleteByStudent(studentId: Int)
}
