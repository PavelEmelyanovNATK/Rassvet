package com.emelyanov.rassvet.shared.domain.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun Date.formatDate() : String{
    val dateFormatter = SimpleDateFormat("dd.MM.yyyy")

    return dateFormatter.format(this)
}

@SuppressLint("SimpleDateFormat")
fun Date.formatJsonDate() : String{
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    return dateFormatter.format(this)
}

@SuppressLint("SimpleDateFormat")
fun Date.formatDay() : Int{
    val dateFormatter = SimpleDateFormat("dd")

    return dateFormatter.format(this).toInt()
}

@SuppressLint("SimpleDateFormat")
fun Date.formatMonth() : Int{
    val dateFormatter = SimpleDateFormat("MM")

    return dateFormatter.format(this).toInt()
}

@SuppressLint("SimpleDateFormat")
fun Date.formatYear() : Int{
    val dateFormatter = SimpleDateFormat("yyyy")

    return dateFormatter.format(this).toInt()
}

@SuppressLint("SimpleDateFormat")
fun Date.formatHours() : String{
    val dateFormatter = SimpleDateFormat("HH")

    return dateFormatter.format(this)
}

@SuppressLint("SimpleDateFormat")
fun Date.formatMinutes() : String{
    val dateFormatter = SimpleDateFormat("mm")

    return dateFormatter.format(this)
}

@SuppressLint("SimpleDateFormat")
fun Date.formatShortTime() : Int{
    val dateFormatter = SimpleDateFormat("HH:mm")

    return dateFormatter.format(this).toInt()
}

@SuppressLint("SimpleDateFormat")
fun getDateFromString(date: String): Date {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    return dateFormatter.parse(date)!!
}

fun getMonthName(month: Int): String =
    when(month){
        1 -> "янв"
        2 -> "фев"
        3 -> "мар"
        4 -> "апр"
        5 -> "мар"
        6 -> "июн"
        7 -> "июл"
        8 -> "авг"
        9 -> "сен"
        10 -> "окт"
        11 -> "ноя"
        12 -> "дек"

        else -> "$month"
    }

fun getMonthIndex(month: String): Int =
    when(month){
        "янв" -> 1
        "фев" -> 2
        "мар" -> 3
        "апр" -> 4
        "мар" -> 5
        "июн" -> 6
        "июл" -> 7
        "авг" -> 8
        "сен" -> 9
        "окт" -> 10
        "ноя" -> 11
        "дек" -> 12

        else -> -1
    }

fun getMonthList(): List<String>
        = listOf(
    "янв",
    "фев",
    "мар",
    "апр",
    "май",
    "июн",
    "июл",
    "авг",
    "сен",
    "окт",
    "ноя",
    "дек"
)

fun getDaysInMonth(month: Int, year: Int)
        = when(month){
    1, 3, 5, 7, 8, 10, 12 -> 31
    2 -> if(isYearLeap(year)) 29 else 28
    else -> 30
}

fun isYearLeap(year: Int) : Boolean
        = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0

@SuppressLint("SimpleDateFormat")
fun Date.formatToBeautifulDateTimeString() : String
= "${this.formatDay()} ${getMonthName(this.formatMonth())} ${this.formatHours()}:${this.formatMinutes()}"

fun getTimeStringFromMinutest(minutes: Int)
= when {
    minutes <= 60 -> "$minutes мин"
    minutes % 60 == 0 -> "${minutes / 60} ч"
    else -> "${minutes / 60} ч ${minutes % 60} мин"
}