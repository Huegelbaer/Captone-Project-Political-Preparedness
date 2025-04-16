package com.example.android.politicalpreparedness.presentation.utils

import android.widget.TextView
import androidx.databinding.BindingAdapter
import java.text.DateFormat
import java.util.Date
import java.util.Locale

@BindingAdapter("usDate")
fun bindTextViewToDate(textView: TextView, date: Date?) {
    textView.text = date?.let {
        val format = DateFormat.getDateInstance(DateFormat.FULL, Locale.US)
        format.format(it)
    } ?: ""
}