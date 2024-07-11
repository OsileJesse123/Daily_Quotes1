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


    private val _quoteIndex = MutableLiveData(-1)

    private val category = MutableLiveData("")

    val quotes: LiveData<List<Quotedataclass>> = category.switchMap {
            categoryName ->
        repository.getQuotesByCategory(categoryName)
    }

    val currentQuote: LiveData<Quotedataclass?> = _quoteIndex.switchMap {
            index ->
        if (!quotes.value.isNullOrEmpty()){
            val myQuote = quotes.value!![index]
            MutableLiveData(myQuote)
        } else{
            MutableLiveData(null)
        }
    }

    fun updateQuoteIndex(){
        if (_quoteIndex.value == -1) _quoteIndex.value = 0
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
    }

    fun previousQuote(){
        _quoteIndex.value?.let {
            index ->
            if (index > 0){
                _quoteIndex.value = index - 1
            }
        }
    }

    fun nextQuote(){
        _quoteIndex.value?.let {
                index ->
            val quotesListSize = quotes.value?.size ?: 0
            if (index < (quotesListSize - 1)){
                _quoteIndex.value = index + 1
            }
        }
    }


    val getAllQuote = repository.getAllFavoriteQuotes()

    val userQuotes = repository.userQuotes()
}