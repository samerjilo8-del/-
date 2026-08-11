package com.talp.smartteacher.data.repository

import com.talp.smartteacher.database.dao.GradeDao
import com.talp.smartteacher.database.entities.Grade
import kotlinx.coroutines.flow.Flow

class GradeRepository(private val gradeDao: GradeDao) {
    suspend fun insertGrade(grade: Grade): Long {
        return gradeDao.insert(grade)
    }

    suspend fun updateGrade(grade: Grade) {
        gradeDao.update(grade)
    }

    suspend fun deleteGrade(grade: Grade) {
        gradeDao.delete(grade)
    }

    fun getGradeById(id: Int): Flow<Grade?> {
        return gradeDao.getGradeById(id)
    }

    fun getGradesByStudent(studentId: Int): Flow<List<Grade>> {
        return gradeDao.getGradesByStudent(studentId)
    }

    fun getGradesByStudentAndSemester(
        studentId: Int,
        semester: Int
    ): Flow<List<Grade>> {
        return gradeDao.getGradesByStudentAndSemester(studentId, semester)
    }

    fun getGradesBySubjectAndSemester(
        subjectId: Int,
        semester: Int
    ): Flow<List<Grade>> {
        return gradeDao.getGradesBySubjectAndSemester(subjectId, semester)
    }

    fun getPendingNotifications(): Flow<List<Grade>> {
        return gradeDao.getPendingNotifications()
    }

    suspend fun markNotificationSent(id: Int) {
        gradeDao.markNotificationSent(id)
    }

    suspend fun deleteByStudent(studentId: Int) {
        gradeDao.deleteByStudent(studentId)
    }
}
