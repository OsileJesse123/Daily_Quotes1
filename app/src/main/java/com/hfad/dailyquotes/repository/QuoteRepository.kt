package com.hfad.dailyquotes.repository

import androidx.lifecycle.LiveData
import com.hfad.dailyquotes.dataClass.Quotedataclass
import com.hfad.dailyquotes.database.QuoteDataBase

class QuoteRepository(private var db: QuoteDataBase) {

    fun userQuotes() = db.motDao().getAllUserQuote()

    suspend fun favoriteQuote(quote: List<Quotedataclass>){
        db.motDao().insert(quote)
    }

    suspend fun insertQuote(quote: Quotedataclass) = db.motDao().inputQuote(quote)
    suspend fun updateQuote(quote: Quotedataclass) = db.motDao().update(quote)
    suspend fun deleteQuote(quote: Quotedataclass) = db.motDao().delete(quote)
    fun getAllFavoriteQuotes() = db.motDao().getFavoriteQuotes()

    fun getQuotesByCategory(category: String) = db.motDao().getQuotesByCategory(category)
}