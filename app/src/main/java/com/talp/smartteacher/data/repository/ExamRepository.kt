package com.talp.smartteacher.data.repository

import com.talp.smartteacher.database.dao.ExamDao
import com.talp.smartteacher.database.entities.Exam
import kotlinx.coroutines.flow.Flow

class ExamRepository(private val examDao: ExamDao) {
    suspend fun insertExam(exam: Exam): Long {
        return examDao.insert(exam)
    }

    suspend fun updateExam(exam: Exam) {
        examDao.update(exam)
    }

    suspend fun deleteExam(exam: Exam) {
        examDao.delete(exam)
    }

    fun getExamById(id: Int): Flow<Exam?> {
        return examDao.getExamById(id)
    }

    fun getExamsByTeacher(teacherId: Int): Flow<List<Exam>> {
        return examDao.getExamsByTeacher(teacherId)
    }

    fun getExamsByTeacherAndSemester(
        teacherId: Int,
        semester: Int
    ): Flow<List<Exam>> {
        return examDao.getExamsByTeacherAndSemester(teacherId, semester)
    }

    fun getExamsBySubject(subjectId: Int): Flow<List<Exam>> {
        return examDao.getExamsBySubject(subjectId)
    }

    fun getExamsByType(type: String): Flow<List<Exam>> {
        return examDao.getExamsByType(type)
    }

    suspend fun deleteAllByTeacher(teacherId: Int) {
        examDao.deleteAllByTeacher(teacherId)
    }
}
