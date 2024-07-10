package com.hfad.dailyquotes.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.hfad.dailyquotes.repository.QuoteRepository

class UserViewModelFactory(private val repository: QuoteRepository)
    : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuoteViewModel::class.java)) {
            return QuoteViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}