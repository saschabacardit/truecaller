package com.tbc.truecallersimple.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tbc.truecallersimple.model.remote.GetPageService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class CountViewModel @Inject constructor(
    private val service: GetPageService
): ViewModel(){
    private var _state = MutableStateFlow(CountStateFlow("", emptyList(), emptyList()))
    val state: StateFlow<CountStateFlow> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                updateData()
            }
        }
    }

    private suspend fun updateData(){
        val data = service.getPage()
        _state.value = CountStateFlow(data, splitWords(data), splitCharacters(data, 15))
    }

    private fun splitWords(data: String): List<String>{
        return data.split("\\s+".toRegex())
    }

    private fun splitCharacters(data: String, splitIndex: Int): List<Char>{
        val splitChars = mutableListOf<Char>()
        data.forEachIndexed{ index, element ->
            if ((index+1)%15==0) {
                splitChars.add(element)
            }
        }
        return splitChars
    }

}
