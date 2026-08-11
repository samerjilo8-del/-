package com.talp.smartteacher.data.repository

import com.talp.smartteacher.database.dao.StudentDao
import com.talp.smartteacher.database.entities.Student
import kotlinx.coroutines.flow.Flow

class StudentRepository(private val studentDao: StudentDao) {
    suspend fun insertStudent(student: Student): Long {
        return studentDao.insert(student)
    }

    suspend fun updateStudent(student: Student) {
        studentDao.update(student)
    }

    suspend fun deleteStudent(student: Student) {
        studentDao.delete(student)
    }

    fun getStudentById(id: Int): Flow<Student?> {
        return studentDao.getStudentById(id)
    }

    fun getStudentByCode(code: String, teacherId: Int): Flow<Student?> {
        return studentDao.getStudentByCode(code, teacherId)
    }

    fun getStudentsByTeacher(teacherId: Int): Flow<List<Student>> {
        return studentDao.getStudentsByTeacher(teacherId)
    }

    fun getStudentsByClass(
        teacherId: Int,
        classNumber: String,
        section: String
    ): Flow<List<Student>> {
        return studentDao.getStudentsByClass(teacherId, classNumber, section)
    }

    suspend fun deleteAllByTeacher(teacherId: Int) {
        studentDao.deleteAllByTeacher(teacherId)
    }
}
