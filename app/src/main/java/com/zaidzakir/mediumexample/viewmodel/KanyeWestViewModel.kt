package com.zaidzakir.mediumexample.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zaidzakir.mediumexample.model.Resource
import com.zaidzakir.mediumexample.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 *Created by Zaid Zakir
 */
@HiltViewModel
class KanyeWestViewModel @Inject constructor(
    val repository: MainRepository
): ViewModel() {

    sealed class QuotesEvent{
        class Success(val resultText:String):QuotesEvent()
        class Failure(val errorText:String):QuotesEvent()
        object Loading:QuotesEvent()
        object Empty:QuotesEvent()
    }

    private val _conversion = MutableStateFlow<QuotesEvent>(QuotesEvent.Empty)
    val conversion: StateFlow<QuotesEvent> = _conversion

    fun getQuotesViewModel(){
        viewModelScope.launch(Dispatchers.IO) {
            _conversion.value = QuotesEvent.Loading
            when(val quotesResponse = repository.getQuotes()) {
                is Resource.Error -> _conversion.value = QuotesEvent.Failure(quotesResponse.message!!)
                is Resource.Success -> {
                    val quote = quotesResponse.data!!.quote
                    if(quote == null) {
                        _conversion.value = QuotesEvent.Failure("Unexpected error")
                    } else {
                        _conversion.value = QuotesEvent.Success(
                            "$quote"
                        )
                    }
                }
            }
        }
    }


}