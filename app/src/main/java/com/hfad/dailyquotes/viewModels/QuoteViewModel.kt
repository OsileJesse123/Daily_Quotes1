package com.hfad.dailyquotes.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.hfad.dailyquotes.dataClass.Quotedataclass
import com.hfad.dailyquotes.repository.QuoteRepository
import kotlinx.coroutines.launch

class QuoteViewModel(private var repository: QuoteRepository): ViewModel() {

    //val getEveryQuote = repository.allQuotes()

    val quotes: LiveData<List<Quotedataclass>>

    val currentQuote: LiveData<Quotedataclass?>

    val _quoteIndex = MutableLiveData(0)
    private val quoteIndex: LiveData<Int> = _quoteIndex

    private val category = MutableLiveData("Life")

    init {
        quotes = category.switchMap {
            categoryName ->
            val theQuotes = repository.getQuotesByCategory(categoryName)
           Log.e("Categories", "Quotes: ${theQuotes.value}")
            theQuotes
        }
        currentQuote = _quoteIndex.switchMap {
            index ->
            if (!quotes.value.isNullOrEmpty()){
                val myQuote = quotes.value!![index]
                Log.e("Quote", "Quote: $myQuote")
                MutableLiveData(myQuote)
            } else{
                MutableLiveData(null)
            }
        }
    }

    fun favoriteQuote(quote:List<Quotedataclass>){
        viewModelScope.launch {
            repository.favoriteQuote(quote)
        }
    }

    fun addQuote(quote: Quotedataclass) =
        viewModelScope.launch {
            repository.insertQuote(quote)
        }
    fun editQuote(quote: Quotedataclass) =
        viewModelScope.launch {
            repository.updateQuote(quote)
        }

    fun removeQuote(quote: Quotedataclass) =
        viewModelScope.launch {
            repository.deleteQuote(quote)
        }

    fun getQuotesByCategory(categoryText: String){
        category.value = categoryText
        Log.e("Category", "Category: ${category.value}")
    }

    fun previousQuote(){
        _quoteIndex.value?.let {
            index ->
            if (index > 0){
                _quoteIndex.value = index - 1
            }
            Log.e("Quote", "Quote: ${currentQuote.value}, index: ${_quoteIndex.value}")
        }
    }

    fun nextQuote(){
        _quoteIndex.value?.let {
                index ->
            if (index < (quotes.value?.size ?: 0)){
                _quoteIndex.value = index + 1
            }
            Log.e("Quote", "Quote: ${currentQuote.value}, index: ${_quoteIndex.value}, quotes: ${quotes.value}")
        }
    }


    val getAllQuote = repository.getAllFavoriteQuotes()

    val userQuotes = repository.userQuotes()
}