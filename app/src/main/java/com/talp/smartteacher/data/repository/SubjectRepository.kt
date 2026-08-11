package com.talp.smartteacher.data.repository

import com.talp.smartteacher.database.dao.SubjectDao
import com.talp.smartteacher.database.entities.Subject
import kotlinx.coroutines.flow.Flow

class SubjectRepository(private val subjectDao: SubjectDao) {
    suspend fun insertSubject(subject: Subject): Long {
        return subjectDao.insert(subject)
    }

    suspend fun updateSubject(subject: Subject) {
        subjectDao.update(subject)
    }

    suspend fun deleteSubject(subject: Subject) {
        subjectDao.delete(subject)
    }

    fun getSubjectById(id: Int): Flow<Subject?> {
        return subjectDao.getSubjectById(id)
    }

    fun getSubjectsByTeacher(teacherId: Int): Flow<List<Subject>> {
        return subjectDao.getSubjectsByTeacher(teacherId)
    }

    fun getSubjectByName(teacherId: Int, name: String): Flow<Subject?> {
        return subjectDao.getSubjectByName(teacherId, name)
    }

    suspend fun deleteAllByTeacher(teacherId: Int) {
        subjectDao.deleteAllByTeacher(teacherId)
    }
}
