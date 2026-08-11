package com.talp.smartteacher.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talp.smartteacher.data.repository.ExamRepository
import com.talp.smartteacher.database.entities.Exam
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ExamViewModel(private val repository: ExamRepository) : ViewModel() {
    private val _exams = MutableStateFlow<List<Exam>>(emptyList())
    val exams: StateFlow<List<Exam>> = _exams.asStateFlow()

    private val _examsByType = MutableStateFlow<List<Exam>>(emptyList())
    val examsByType: StateFlow<List<Exam>> = _examsByType.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun loadExamsByTeacher(teacherId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getExamsByTeacher(teacherId).collect { examList ->
                    _exams.value = examList
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadExamsByTeacherAndSemester(teacherId: Int, semester: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getExamsByTeacherAndSemester(teacherId, semester).collect { examList ->
                    _exams.value = examList
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadExamsByType(type: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getExamsByType(type).collect { examList ->
                    _examsByType.value = examList
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addExam(exam: Exam) {
        viewModelScope.launch {
            try {
                repository.insertExam(exam)
                loadExamsByTeacher(exam.teacherId)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun updateExam(exam: Exam) {
        viewModelScope.launch {
            try {
                repository.updateExam(exam)
                loadExamsByTeacher(exam.teacherId)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun deleteExam(exam: Exam) {
        viewModelScope.launch {
            try {
                repository.deleteExam(exam)
                loadExamsByTeacher(exam.teacherId)
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
