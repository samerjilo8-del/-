package com.talp.smartteacher.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talp.smartteacher.data.repository.HomeworkRepository
import com.talp.smartteacher.database.entities.Homework
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeworkViewModel(private val repository: HomeworkRepository) : ViewModel() {
    private val _homework = MutableStateFlow<List<Homework>>(emptyList())
    val homework: StateFlow<List<Homework>> = _homework.asStateFlow()

    private val _homeworkBySubject = MutableStateFlow<List<Homework>>(emptyList())
    val homeworkBySubject: StateFlow<List<Homework>> = _homeworkBySubject.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun loadHomeworkByTeacher(teacherId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getHomeworkByTeacher(teacherId).collect { homeworkList ->
                    _homework.value = homeworkList
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadHomeworkBySubject(subjectId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getHomeworkBySubject(subjectId).collect { homeworkList ->
                    _homeworkBySubject.value = homeworkList
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addHomework(homework: Homework) {
        viewModelScope.launch {
            try {
                repository.insertHomework(homework)
                loadHomeworkByTeacher(homework.teacherId)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun updateHomework(homework: Homework) {
        viewModelScope.launch {
            try {
                repository.updateHomework(homework)
                loadHomeworkByTeacher(homework.teacherId)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun deleteHomework(homework: Homework) {
        viewModelScope.launch {
            try {
                repository.deleteHomework(homework)
                loadHomeworkByTeacher(homework.teacherId)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun markNotificationSent(id: Int) {
        viewModelScope.launch {
            try {
                repository.markNotificationSent(id)
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }
}
