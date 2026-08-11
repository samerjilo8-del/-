package com.talp.smartteacher.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.talp.smartteacher.database.entities.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {
    @Insert
    suspend fun insert(subject: Subject): Long

    @Update
    suspend fun update(subject: Subject)

    @Delete
    suspend fun delete(subject: Subject)

    @Query("SELECT * FROM subjects WHERE id = :id")
    fun getSubjectById(id: Int): Flow<Subject?>

    @Query("SELECT * FROM subjects WHERE teacherId = :teacherId ORDER BY name ASC")
    fun getSubjectsByTeacher(teacherId: Int): Flow<List<Subject>>

    @Query("SELECT * FROM subjects WHERE teacherId = :teacherId AND name = :name")
    fun getSubjectByName(teacherId: Int, name: String): Flow<Subject?>

    @Query("DELETE FROM subjects WHERE teacherId = :teacherId")
    suspend fun deleteAllByTeacher(teacherId: Int)
}
