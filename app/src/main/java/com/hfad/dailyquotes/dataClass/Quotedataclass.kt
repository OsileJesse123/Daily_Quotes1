package com.hfad.dailyquotes.dataClass

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "quote_table")
data class Quotedataclass(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var quoteText: String = "",
    var quoteAuthor: String = "",
    var isFavorite: Boolean = false,
    val category: String
)

enum class QuoteCategory{
    Life,
    Love,
    Success,
    English,
    Angry,
    Tired,
    Sad,
    Happy,
    Motivation,
    Funny
}