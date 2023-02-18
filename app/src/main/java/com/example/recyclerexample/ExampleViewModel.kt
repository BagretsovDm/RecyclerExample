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

    init {
        updateFirstCount()
    }

    private fun updateFirstCount() {
        var count = 0
        positionList.value!!.forEach { count += it.first }
        _firstCount.postValue(count)
    }

    fun updateList(position: Int) {
        val currentList = _positionList.value!!
        currentList[position].first++
        _positionList.postValue(currentList)
        updateFirstCount()
    }

    fun addPosition() {
        val currentList = _positionList.value!!
        currentList.add(Model())
        _positionList.postValue(currentList)
        updateFirstCount()
    }
}
