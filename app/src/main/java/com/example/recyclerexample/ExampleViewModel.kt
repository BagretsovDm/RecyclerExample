package com.example.recyclerexample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExampleViewModel : ViewModel() {

    private val _positionList = MutableLiveData(
        mutableListOf(
            Model()
        )
    )

    private val _firstCount = MutableLiveData(0)
    private val _secondCount = MutableLiveData(0)

    val firstCount: LiveData<Int> = _firstCount
    val secondCount: LiveData<Int> = _secondCount
    val positionList: LiveData<MutableList<Model>> = _positionList

    fun addPosition() {
        val currentList = _positionList.value!!
        currentList.add(Model())
        _positionList.postValue(currentList)
    }
}
