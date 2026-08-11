package com.talp.smartteacher.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.talp.smartteacher.database.entities.Schedule
import kotlinx.coroutines.flow.Flow

@Dao
interface ScheduleDao {
    @Insert
    suspend fun insert(schedule: Schedule): Long

    @Update
    suspend fun update(schedule: Schedule)

    @Delete
    suspend fun delete(schedule: Schedule)

    @Query("SELECT * FROM schedule WHERE id = :id")
    fun getScheduleById(id: Int): Flow<Schedule?>

    @Query("SELECT * FROM schedule WHERE teacherId = :teacherId ORDER BY dayOfWeek ASC, periodNumber ASC")
    fun getScheduleByTeacher(teacherId: Int): Flow<List<Schedule>>

    @Query("SELECT * FROM schedule WHERE teacherId = :teacherId AND dayOfWeek = :dayOfWeek ORDER BY periodNumber ASC")
    fun getScheduleByDay(teacherId: Int, dayOfWeek: Int): Flow<List<Schedule>>

    @Query("DELETE FROM schedule WHERE teacherId = :teacherId")
    suspend fun deleteAllByTeacher(teacherId: Int)

    @Query("DELETE FROM schedule WHERE teacherId = :teacherId AND dayOfWeek = :dayOfWeek AND periodNumber = :periodNumber")
    suspend fun deleteByDayAndPeriod(teacherId: Int, dayOfWeek: Int, periodNumber: Int)
}
