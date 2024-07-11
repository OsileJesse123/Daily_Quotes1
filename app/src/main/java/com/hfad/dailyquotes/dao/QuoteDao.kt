package com.hfad.dailyquotes.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.hfad.dailyquotes.dataClass.Quotedataclass

@Dao
interface QuoteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(quote: List<Quotedataclass>)

    @Insert
    suspend fun inputQuote(user: Quotedataclass)

    @Update
    suspend fun update(quote: Quotedataclass)

    @Delete
    suspend fun delete(quote: Quotedataclass)

    @Query("SELECT * FROM quote_table WHERE :category = category")
    fun getQuotesByCategory(category: String): LiveData<List<Quotedataclass>>


    @Query("SELECT * FROM quote_table WHERE category = 'name' ORDER BY id DESC")
    fun getAllUserQuote(): LiveData<List<Quotedataclass>>

    @Query("SELECT * FROM quote_table  WHERE isFavorite = 1")
    fun getFavoriteQuotes(): LiveData<List<Quotedataclass>>
}