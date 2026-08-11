package com.talp.smartteacher.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.talp.smartteacher.database.entities.Homework
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeworkDao {
    @Insert
    suspend fun insert(homework: Homework): Long

    @Update
    suspend fun update(homework: Homework)

    @Delete
    suspend fun delete(homework: Homework)

    @Query("SELECT * FROM homework WHERE id = :id")
    fun getHomeworkById(id: Int): Flow<Homework?>

    @Query("SELECT * FROM homework WHERE teacherId = :teacherId ORDER BY dueDate ASC")
    fun getHomeworkByTeacher(teacherId: Int): Flow<List<Homework>>

    @Query("SELECT * FROM homework WHERE subjectId = :subjectId ORDER BY dueDate ASC")
    fun getHomeworkBySubject(subjectId: Int): Flow<List<Homework>>

    @Query("SELECT * FROM homework WHERE notificationSent = 0 ORDER BY createdAt ASC")
    fun getPendingNotifications(): Flow<List<Homework>>

    @Query("UPDATE homework SET notificationSent = 1 WHERE id = :id")
    suspend fun markNotificationSent(id: Int)

    @Query("DELETE FROM homework WHERE teacherId = :teacherId")
    suspend fun deleteAllByTeacher(teacherId: Int)
}
