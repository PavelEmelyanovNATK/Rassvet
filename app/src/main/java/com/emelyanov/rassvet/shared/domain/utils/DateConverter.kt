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
fun getDateFromString(date: String): Date {
    val dateFormatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    return dateFormatter.parse(date)!!
}