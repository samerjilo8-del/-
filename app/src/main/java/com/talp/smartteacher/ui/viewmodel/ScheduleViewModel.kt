package com.talp.smartteacher.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.talp.smartteacher.data.repository.ScheduleRepository
import com.talp.smartteacher.database.entities.Schedule
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ScheduleViewModel(private val repository: ScheduleRepository) : ViewModel() {
    private val _schedule = MutableStateFlow<List<Schedule>>(emptyList())
    val schedule: StateFlow<List<Schedule>> = _schedule.asStateFlow()

    private val _scheduleByDay = MutableStateFlow<List<Schedule>>(emptyList())
    val scheduleByDay: StateFlow<List<Schedule>> = _scheduleByDay.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    fun loadScheduleByTeacher(teacherId: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getScheduleByTeacher(teacherId).collect { scheduleList ->
                    _schedule.value = scheduleList
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun loadScheduleByDay(teacherId: Int, dayOfWeek: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                repository.getScheduleByDay(teacherId, dayOfWeek).collect { scheduleList ->
                    _scheduleByDay.value = scheduleList
                }
            } catch (e: Exception) {
                _errorMessage.value = e.message
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun addSchedule(schedule: Schedule) {
        viewModelScope.launch {
            try {
                repository.insertSchedule(schedule)
                loadScheduleByTeacher(schedule.teacherId)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun updateSchedule(schedule: Schedule) {
        viewModelScope.launch {
            try {
                repository.updateSchedule(schedule)
                loadScheduleByTeacher(schedule.teacherId)
                _errorMessage.value = null
            } catch (e: Exception) {
                _errorMessage.value = e.message
            }
        }
    }

    fun deleteSchedule(schedule: Schedule) {
        viewModelScope.launch {
            try {
                repository.deleteSchedule(schedule)
                loadScheduleByTeacher(schedule.teacherId)
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
