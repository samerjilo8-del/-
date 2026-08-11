package com.talp.smartteacher.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talp.smartteacher.data.repository.TeacherRepository
import com.talp.smartteacher.database.entities.Teacher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class TeacherViewModel(private val repository: TeacherRepository) : ViewModel() {
    private val _currentTeacher = MutableStateFlow<Teacher?>(null)
    val currentTeacher: StateFlow<Teacher?> = _currentTeacher.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    private val _loginSuccess = MutableStateFlow(false)
    val loginSuccess: StateFlow<Boolean> = _loginSuccess.asStateFlow()

    init {
        loadCurrentTeacher()
    }

    private fun loadCurrentTeacher() {
        viewModelScope.launch {
            repository.getCurrentTeacher().collect { teacher ->
                _currentTeacher.value = teacher
            }
        }
    }

    fun loginTeacher(username: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val teacher = repository.authenticateTeacher(username, password)
                if (teacher != null) {
                    _currentTeacher.value = teacher
                    _loginSuccess.value = true
                    _errorMessage.value = null
                } else {
                    _errorMessage.value = "بيانات دخول غير صحيحة"
                    _loginSuccess.value = false
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
                _loginSuccess.value = false
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updatePassword(id: Int, newPassword: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.updatePassword(id, newPassword)
                _currentTeacher.value = _currentTeacher.value?.copy(password = newPassword)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun updateClassInfo(id: Int, classNumber: String, section: String) {
        viewModelScope.launch {
            try {
                repository.updateClassInfo(id, classNumber, section)
                _currentTeacher.value = _currentTeacher.value?.copy(
                    classNumber = classNumber,
                    section = section
                )
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }

    fun logout() {
        _currentTeacher.value = null
        _loginSuccess.value = false
    }
}
