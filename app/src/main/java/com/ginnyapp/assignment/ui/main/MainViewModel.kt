package com.ginnyapp.assignment.ui.main

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.ginnyapp.assignment.data.model.NumberModel
import com.ginnyapp.assignment.data.remote.DataResponse
import com.ginnyapp.assignment.data.repository.MainRepository
import com.ginnyapp.assignment.util.Resource
import kotlinx.coroutines.launch

private const val TAG = "MainViewModel"

class MainViewModel @ViewModelInject constructor(
        private val repository: MainRepository
) : ViewModel() {

    private val _numbers = MutableLiveData<List<NumberModel>>()

    val numbers = _numbers.map {
        val setNums = it.extractToSetOfInts()
        it.sortedBy { it.number }.map {
            if (setNums.contains(-(it.number)))
                 DataTypeHolder(2, it.number.toString())
            else  DataTypeHolder(1, it.number.toString())
        }
    }

    private val _spinner = MutableLiveData<Boolean>(false)

    val spinner: LiveData<Boolean>
        get() = _spinner

    private val _toastMsg = MutableLiveData<String?>()

    val toastMsg: LiveData<String?>
        get() = _toastMsg

    init {
        fetchNumbers()
    }

    private fun fetchNumbers() {
        launchDataLoad {
            repository.fetchNumbers()
        }
    }

    private fun launchDataLoad(block: suspend () -> Resource<DataResponse>) {
        viewModelScope.launch {
            _spinner.value = true

            val result = block()

            when (result.status) {
                Resource.Status.LOADING ->  _spinner.value = true

                Resource.Status.SUCCESS -> {
                    if (!result.data?.numbers.isNullOrEmpty()) {
                       _numbers.value = result.data?.numbers
                    }
                }

                Resource.Status.ERROR -> {
                    _toastMsg.value = result.message
                }
            }

            _spinner.value = false

        }
    }

}

fun List<NumberModel>.extractToSetOfInts() : Set<Int> {
    val setNums = HashSet<Int>()
    forEach { if (it.number != 0) setNums.add(it.number) }
    return setNums
}