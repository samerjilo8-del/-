package com.talp.smartteacher.data.repository

import com.talp.smartteacher.database.dao.NoteDao
import com.talp.smartteacher.database.entities.Note
import kotlinx.coroutines.flow.Flow

class NoteRepository(private val noteDao: NoteDao) {
    suspend fun insertNote(note: Note): Long {
        return noteDao.insert(note)
    }

    suspend fun updateNote(note: Note) {
        noteDao.update(note)
    }

    suspend fun deleteNote(note: Note) {
        noteDao.delete(note)
    }

    fun getNoteById(id: Int): Flow<Note?> {
        return noteDao.getNoteById(id)
    }

    fun getNotesByStudent(studentId: Int): Flow<List<Note>> {
        return noteDao.getNotesByStudent(studentId)
    }

    fun getNotesByTeacher(teacherId: Int): Flow<List<Note>> {
        return noteDao.getNotesByTeacher(teacherId)
    }

    fun getPendingNotifications(): Flow<List<Note>> {
        return noteDao.getPendingNotifications()
    }

    suspend fun markNotificationSent(id: Int) {
        noteDao.markNotificationSent(id)
    }

    suspend fun deleteByStudent(studentId: Int) {
        noteDao.deleteByStudent(studentId)
    }

    suspend fun deleteByTeacher(teacherId: Int) {
        noteDao.deleteByTeacher(teacherId)
    }
}
