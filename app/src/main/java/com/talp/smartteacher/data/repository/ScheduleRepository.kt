package com.talp.smartteacher.data.repository

import com.talp.smartteacher.database.dao.ScheduleDao
import com.talp.smartteacher.database.entities.Schedule
import kotlinx.coroutines.flow.Flow

class ScheduleRepository(private val scheduleDao: ScheduleDao) {
    suspend fun insertSchedule(schedule: Schedule): Long {
        return scheduleDao.insert(schedule)
    }

    suspend fun updateSchedule(schedule: Schedule) {
        scheduleDao.update(schedule)
    }

    suspend fun deleteSchedule(schedule: Schedule) {
        scheduleDao.delete(schedule)
    }

    fun getScheduleById(id: Int): Flow<Schedule?> {
        return scheduleDao.getScheduleById(id)
    }

    fun getScheduleByTeacher(teacherId: Int): Flow<List<Schedule>> {
        return scheduleDao.getScheduleByTeacher(teacherId)
    }

    fun getScheduleByDay(teacherId: Int, dayOfWeek: Int): Flow<List<Schedule>> {
        return scheduleDao.getScheduleByDay(teacherId, dayOfWeek)
    }

    suspend fun deleteAllByTeacher(teacherId: Int) {
        scheduleDao.deleteAllByTeacher(teacherId)
    }

    suspend fun deleteByDayAndPeriod(
        teacherId: Int,
        dayOfWeek: Int,
        periodNumber: Int
    ) {
        scheduleDao.deleteByDayAndPeriod(teacherId, dayOfWeek, periodNumber)
    }
}
