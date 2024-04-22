package com.example.petcareapp

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


fun convertToUnixTime(selectedDate: String, selectedTime: String): Long {
    val dateTimeString = "$selectedDate $selectedTime"
    val dateFormat = SimpleDateFormat("MMM dd,yyyy HH:mm", Locale.ENGLISH)
    val dateTime = dateFormat.parse(dateTimeString)
    return dateTime?.time ?: -1 // Return -1 if parsing fails
}

fun getDateFromUnixTime(unixTime: Long): String {
    val format = "MMM dd, yyyy" // Date format
    val dateFormat = SimpleDateFormat(format, Locale.ENGLISH)
    return dateFormat.format(Date(unixTime))
}

fun getTimeFromUnixTime(unixTime: Long): String {
    val timeFormat = "HH:mm" // Time format
    val dateFormat = SimpleDateFormat(timeFormat, Locale.ENGLISH)
    return dateFormat.format(Date(unixTime))
}

fun getTimeWithAmPm(timeString: String): String {
    val inputFormat = "HH:mm"
    val outputFormat = "hh:mm a"
    val inputDateFormat = SimpleDateFormat(inputFormat, Locale.ENGLISH)
    val outputDateFormat = SimpleDateFormat(outputFormat, Locale.ENGLISH)
    val date = inputDateFormat.parse(timeString)
    return outputDateFormat.format(date!!)
}