package com.example.android.politicalpreparedness.data.remote.jsonadapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.text.SimpleDateFormat
import java.util.Date
import java.util.TimeZone

class DateAdapter {

    private var dateFormat = SimpleDateFormat("yyyy-MM-dd").apply {
        timeZone = TimeZone.getTimeZone("GMT")
    }
    @FromJson
    fun dateFromJson (date: String): Date? {
        return dateFormat.parse(date)
    }

    @ToJson
    fun dateToJson(date: Date): String {
        return dateFormat.format(date)
    }
}