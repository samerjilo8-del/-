package com.talp.smartteacher.data.repository

import com.talp.smartteacher.database.dao.HomeworkDao
import com.talp.smartteacher.database.entities.Homework
import kotlinx.coroutines.flow.Flow

class HomeworkRepository(private val homeworkDao: HomeworkDao) {
    suspend fun insertHomework(homework: Homework): Long {
        return homeworkDao.insert(homework)
    }

    suspend fun updateHomework(homework: Homework) {
        homeworkDao.update(homework)
    }

    suspend fun deleteHomework(homework: Homework) {
        homeworkDao.delete(homework)
    }

    fun getHomeworkById(id: Int): Flow<Homework?> {
        return homeworkDao.getHomeworkById(id)
    }

    fun getHomeworkByTeacher(teacherId: Int): Flow<List<Homework>> {
        return homeworkDao.getHomeworkByTeacher(teacherId)
    }

    fun getHomeworkBySubject(subjectId: Int): Flow<List<Homework>> {
        return homeworkDao.getHomeworkBySubject(subjectId)
    }

    fun getPendingNotifications(): Flow<List<Homework>> {
        return homeworkDao.getPendingNotifications()
    }

    suspend fun markNotificationSent(id: Int) {
        homeworkDao.markNotificationSent(id)
    }

    suspend fun deleteAllByTeacher(teacherId: Int) {
        homeworkDao.deleteAllByTeacher(teacherId)
    }
}
