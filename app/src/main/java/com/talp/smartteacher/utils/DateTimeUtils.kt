package com.talp.smartteacher.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object DateTimeUtils {
    fun getFormattedDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("ar"))
        return sdf.format(timestamp)
    }

    fun getFormattedDateTime(timestamp: Long): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale("ar"))
        return sdf.format(timestamp)
    }

    fun getDayName(dayOfWeek: Int): String {
        return when (dayOfWeek) {
            0 -> "الأحد"
            1 -> "الاثنين"
            2 -> "الثلاثاء"
            3 -> "الأربعاء"
            4 -> "الخميس"
            else -> ""
        }
    }

    fun getCurrentHijriDate(): String {
        // Simplified Hijri conversion (requires library for full support)
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return "$day/$month/$year"
    }

    fun getCurrentGregorianDate(): String {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return sdf.format(System.currentTimeMillis())
    }

    fun getCurrentDayOfWeek(): String {
        val calendar = Calendar.getInstance()
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK) - 1
        return getDayName(if (dayOfWeek == 6) 0 else dayOfWeek)
    }
}
