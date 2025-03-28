package com.tbc.truecallersimple.viewmodel

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
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
import java.lang.Thread.sleep
import javax.inject.Inject


@HiltViewModel
class CountViewModel @Inject constructor(
    private val service: GetPageService
): ViewModel(){
    private var _state = MutableStateFlow(CountStateFlow("", emptyMap(), emptyList()))
    val state: StateFlow<CountStateFlow> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                while(_state.value.page.isEmpty()){
                    updateData()
                    sleep(200)
                }
            }
        }
    }

    private suspend fun updateData(){
        val data = service.getPage()
        _state.value = CountStateFlow(data, simpleCountWords(data), splitCharacters(data, 15))
    }

    @Deprecated("This code is quite complex..., we should use simpleCountWords()")
    fun performantSplitWords(data: String): Map<String, Int>{
        val dataLowercase = data.toLowerCase(Locale.current)
        val countMap: MutableMap<String, Int> = HashMap()
        var currentString = ""
        var index = 0
        while(index<dataLowercase.length){
            if (dataLowercase[index].isWhitespace()){
                countMap.merge(currentString, 1) { i: Int?, i1: Int? -> Integer.sum(i!!, i1!!) }
                currentString = ""
            } else {
                currentString+=dataLowercase[index]
            }
            index++
        }
        countMap.merge(currentString, 1) { i: Int?, i1: Int? -> Integer.sum(i!!, i1!!) }
        return countMap
    }

    /**
     * While this function is slightly less performant than performantSplitWords
     * it is much easier to read and ore kotlin-esque in design, likely a better solution
     * for non-mission critical functions
     * */
    fun simpleCountWords(data: String): Map<String, Int> {
        return data.toLowerCase(Locale.current).split("\\s+".toRegex()).groupingBy { it }.eachCount()
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
