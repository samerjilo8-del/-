package com.talp.smartteacher.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talp.smartteacher.data.repository.StudentRepository
import com.talp.smartteacher.database.entities.Student
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StudentViewModel(private val repository: StudentRepository) : ViewModel() {
    private val _students = MutableStateFlow<List<Student>>(emptyList())
    val students: StateFlow<List<Student>> = _students.asStateFlow()

    private val _selectedStudent = MutableStateFlow<Student?>(null)
    val selectedStudent: StateFlow<Student?> = _selectedStudent.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun loadStudentsByTeacher(teacherId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getStudentsByTeacher(teacherId).collect { studentList ->
                    _students.value = studentList
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadStudentsByClass(teacherId: Int, classNumber: String, section: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getStudentsByClass(teacherId, classNumber, section).collect { studentList ->
                    _students.value = studentList
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addStudent(student: Student) {
        viewModelScope.launch {
            try {
                repository.insertStudent(student)
                loadStudentsByTeacher(student.teacherId)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun updateStudent(student: Student) {
        viewModelScope.launch {
            try {
                repository.updateStudent(student)
                loadStudentsByTeacher(student.teacherId)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun deleteStudent(student: Student) {
        viewModelScope.launch {
            try {
                repository.deleteStudent(student)
                loadStudentsByTeacher(student.teacherId)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun selectStudent(student: Student) {
        _selectedStudent.value = student
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
