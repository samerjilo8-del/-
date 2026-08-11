package com.talp.smartteacher.data.repository

import com.talp.smartteacher.database.dao.TeacherDao
import com.talp.smartteacher.database.entities.Teacher
import kotlinx.coroutines.flow.Flow

class TeacherRepository(private val teacherDao: TeacherDao) {
    suspend fun insertTeacher(teacher: Teacher): Long {
        return teacherDao.insert(teacher)
    }

    suspend fun updateTeacher(teacher: Teacher) {
        teacherDao.update(teacher)
    }

    suspend fun deleteTeacher(teacher: Teacher) {
        teacherDao.delete(teacher)
    }

    fun getTeacherById(id: Int): Flow<Teacher?> {
        return teacherDao.getTeacherById(id)
    }

    fun getTeacherByUsername(username: String): Flow<Teacher?> {
        return teacherDao.getTeacherByUsername(username)
    }

    fun getCurrentTeacher(): Flow<Teacher?> {
        return teacherDao.getCurrentTeacher()
    }

    suspend fun updatePassword(id: Int, newPassword: String) {
        teacherDao.updatePassword(id, newPassword)
    }

    suspend fun updateClassInfo(id: Int, classNumber: String, section: String) {
        teacherDao.updateClassInfo(id, classNumber, section)
    }

    suspend fun authenticateTeacher(username: String, password: String): Teacher? {
        var teacher: Teacher? = null
        val flow = getTeacherByUsername(username)
        flow.collect { foundTeacher ->
            if (foundTeacher != null && foundTeacher.password == password) {
                teacher = foundTeacher
            }
        }
        return teacher
    }
}
