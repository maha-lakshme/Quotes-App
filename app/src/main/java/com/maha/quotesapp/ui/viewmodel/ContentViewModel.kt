package com.maha.quotesapp.ui.viewmodel


import androidx.lifecycle.*
import com.maha.quotesapp.data.model.Quote
import com.maha.quotesapp.data.repository.ContentRepository
import kotlinx.coroutines.launch

class ContentViewModel(private val repository: ContentRepository) : ViewModel() {

    private val _quotes = MutableLiveData<List<Quote>>()
    val quotes: LiveData<List<Quote>> = _quotes

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    // Load quotes asynchronously
    fun loadQuotes() {
        viewModelScope.launch {
            _loading.value = true
            try {
                _quotes.value = repository.fetchQuotes()
            } catch (e: Exception) {
                _error.value = e.localizedMessage
            } finally {
                _loading.value = false
            }
        }
    }
}
