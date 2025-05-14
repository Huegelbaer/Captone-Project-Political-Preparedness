package com.example.android.politicalpreparedness.presentation.utils

import android.graphics.drawable.Drawable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
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

@BindingAdapter(
    value = ["isFavorite",
        "favoriteIcon",
        "favoriteDescription",
        "favoriteNotIcon",
        "favoriteNotDescription"
    ], requireAll = true
)
fun bindFabFavoriteState(
    fab: FloatingActionButton,
    isFavorite: Boolean,
    favoriteIcon: Drawable,
    favoriteDescription: CharSequence,
    favoriteNotIcon: Drawable,
    favoriteNotDescription: CharSequence
) {
    if (isFavorite) {
        fab.setImageDrawable(favoriteIcon)
        fab.contentDescription = favoriteDescription
    } else {
        fab.setImageDrawable(favoriteNotIcon)
        fab.contentDescription = favoriteNotDescription
    }
}