package com.talp.smartteacher.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.talp.smartteacher.database.entities.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note): Long

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM notes WHERE id = :id")
    fun getNoteById(id: Int): Flow<Note?>

    @Query("SELECT * FROM notes WHERE studentId = :studentId ORDER BY createdAt DESC")
    fun getNotesByStudent(studentId: Int): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE teacherId = :teacherId ORDER BY createdAt DESC")
    fun getNotesByTeacher(teacherId: Int): Flow<List<Note>>

    @Query("SELECT * FROM notes WHERE notificationSent = 0 ORDER BY createdAt ASC")
    fun getPendingNotifications(): Flow<List<Note>>

    @Query("UPDATE notes SET notificationSent = 1 WHERE id = :id")
    suspend fun markNotificationSent(id: Int)

    @Query("DELETE FROM notes WHERE studentId = :studentId")
    suspend fun deleteByStudent(studentId: Int)

    @Query("DELETE FROM notes WHERE teacherId = :teacherId")
    suspend fun deleteByTeacher(teacherId: Int)
}
