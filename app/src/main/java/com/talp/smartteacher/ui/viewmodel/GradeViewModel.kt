package com.talp.smartteacher.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talp.smartteacher.data.repository.GradeRepository
import com.talp.smartteacher.database.entities.Grade
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GradeViewModel(private val repository: GradeRepository) : ViewModel() {
    private val _grades = MutableStateFlow<List<Grade>>(emptyList())
    val grades: StateFlow<List<Grade>> = _grades.asStateFlow()

    private val _gradesByStudent = MutableStateFlow<List<Grade>>(emptyList())
    val gradesByStudent: StateFlow<List<Grade>> = _gradesByStudent.asStateFlow()

    private val _gradesBySubject = MutableStateFlow<List<Grade>>(emptyList())
    val gradesBySubject: StateFlow<List<Grade>> = _gradesBySubject.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun loadGradesByStudent(studentId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getGradesByStudent(studentId).collect { gradeList ->
                    _gradesByStudent.value = gradeList
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadGradesByStudentAndSemester(studentId: Int, semester: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getGradesByStudentAndSemester(studentId, semester).collect { gradeList ->
                    _grades.value = gradeList
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadGradesBySubjectAndSemester(subjectId: Int, semester: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getGradesBySubjectAndSemester(subjectId, semester).collect { gradeList ->
                    _gradesBySubject.value = gradeList
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addGrade(grade: Grade) {
        viewModelScope.launch {
            try {
                repository.insertGrade(grade)
                loadGradesByStudentAndSemester(grade.studentId, grade.semester)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun updateGrade(grade: Grade) {
        viewModelScope.launch {
            try {
                repository.updateGrade(grade)
                loadGradesByStudentAndSemester(grade.studentId, grade.semester)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun deleteGrade(grade: Grade) {
        viewModelScope.launch {
            try {
                repository.deleteGrade(grade)
                loadGradesByStudentAndSemester(grade.studentId, grade.semester)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
